package com.board.gd.domain.post;

/**
 * Created by gd.godong9 on 2017. 4. 4.
 */

import com.board.gd.domain.stock.Stock;
import com.board.gd.domain.stock.StockService;
import com.board.gd.domain.user.User;
import com.board.gd.domain.user.UserService;
import com.board.gd.exception.PostException;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Transactional(readOnly = true)
@Service
public class PostService {
    private static final int MAX_TITLE_SIZE = 50;
    private static final int MAX_CONTENT_SIZE = 10000;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostLikeRepository postLikeRepository;

    @Autowired
    private PostReportRepository postReportRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private StockService stockService;

    public Post findOne(Long id) {
        return findOne(id, null);
    }

    public Post findOne(Long id, Long userId) {
        Post post = postRepository.findOne(id);
        if (Objects.isNull(userId)) {
            return post;
        }
        List<PostLike> postLikes = postLikeRepository.findByPostIdAndUserId(id, userId);
        if (postLikes.size() == 0) {
            return post;
        }
        postLikes.forEach(postLike -> {
                    if (postLike.getUnlike()) {
                        post.setIsUnliked(true);
                    } else {
                        post.setIsLiked(true);
                    }
                });
        return post;
    }

    @Transactional(readOnly = false)
    public Post increaseViewCountAndFindOne(Long id) {
        Post post = postRepository.findOne(id);
        if (Objects.isNull(post)) {
            throw new PostException("Not exist post!");
        }
        post.setViewCount(post.getViewCount() + 1);
        return postRepository.save(post);
    }

    @Transactional(readOnly = false)
    public void increaseCommentCount(Post post) {
        post.setCommentCount(post.getCommentCount() + 1);
        postRepository.save(post);
    }

    @Transactional(readOnly = false)
    public void increasePostLikeCount(Post post) {
        post.setPostLikeCount(post.getPostLikeCount() + 1);
        postRepository.save(post);
    }

    @Transactional(readOnly = false)
    public void increasePostUnlikeCount(Post post) {
        post.setPostUnlikeCount(post.getPostUnlikeCount() + 1);
        postRepository.save(post);
    }

    @Transactional(readOnly = false)
    public void blockPost(Post post) {
        post.setBlocked(true);
        postRepository.save(post);
    }

    public Page<Post> findAll(Predicate predicate, Pageable pageable) {
        if (Objects.isNull(pageable)) {
            throw new PostException("Invalid pageable!");
        }
        return postRepository.findAll(predicate, pageable);
    }

    @Transactional(readOnly = false)
    public Post create(PostDto postDto) {
        User user = userService.findOne(postDto.getUserId());
        if (Objects.isNull(user)) {
            throw new PostException("Not exist user!");
        }

        Stock stock = null;
        if (!Objects.isNull(postDto.getStockId())) {
            stock = stockService.findOne(postDto.getStockId());
        }

        if (postDto.getTitle().length() > MAX_TITLE_SIZE || postDto.getContent().length() > MAX_CONTENT_SIZE) {
            throw new PostException("Text size too long!");
        }

        return postRepository.save(Post.builder()
                .type(PostType.FREE) // 디폴트로 FREE로 세팅
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .viewCount(0L)
                .commentCount(0L)
                .postLikeCount(0L)
                .postUnlikeCount(0L)
                .blocked(false)
                .user(user)
                .stock(stock)
                .build());
    }

    @Transactional(readOnly = false)
    public PostLike createPostLike(PostLikeDto postLikeDto) {
        User user = userService.findOne(postLikeDto.getUserId());
        if (Objects.isNull(user)) {
            throw new PostException("Not exist user!");
        }

        Post post = findOne(postLikeDto.getPostId());
        if (Objects.isNull(post)) {
            throw new PostException("Not exist post!");
        }

        PostLike postLike = postLikeRepository.findByPostIdAndUserIdAndUnlike(post.getId(), user.getId(), postLikeDto.getUnlike());
        if (!Objects.isNull(postLike)) {
            throw new PostException("Already post like or unlike!");
        }

        if (postLikeDto.getUnlike()) {
            increasePostUnlikeCount(post);
        } else {
            increasePostLikeCount(post);
        }

        return postLikeRepository.save(PostLike.builder()
                .post(post)
                .user(user)
                .unlike(postLikeDto.getUnlike())
                .build());
    }

    @Transactional(readOnly = false)
    public PostReport createPostReport(PostReportDto postReportDto) {
        User user = userService.findOne(postReportDto.getUserId());
        if (Objects.isNull(user)) {
            throw new PostException("Not exist user!");
        }

        Post post = findOne(postReportDto.getPostId());
        if (Objects.isNull(post)) {
            throw new PostException("Not exist post!");
        }

        PostReport postReport = postReportRepository.findByPostIdAndUserId(post.getId(), user.getId());
        if (!Objects.isNull(postReport)) {
            throw new PostException("Already post report!");
        }

        postReport = postReportRepository.save(PostReport.builder()
                .post(post)
                .user(user)
                .content(postReportDto.getContent())
                .build());

        int reportCount = postReportRepository.countByPostId(post.getId());
        if (reportCount > 2) { // 신고횟수가 3이상이면 블락 처리
            blockPost(post);
        }

        return postReport;
    }

    @Transactional(readOnly = false)
    public Post update(PostDto postDto) {
        Post post = postRepository.findOne(postDto.getId());
        if (Objects.isNull(post)) {
            throw new PostException("Not exist post!");
        }
        if (ObjectUtils.notEqual(postDto.getUserId(), post.getUser().getId())) {
            throw new PostException("Not allowed!");
        }

        if (!Objects.isNull(postDto.getTitle())) {
            post.setTitle(postDto.getTitle());
        }

        if (!Objects.isNull(postDto.getContent())) {
            post.setContent(postDto.getContent());
        }

        if (!Objects.isNull(postDto.getStockId())) {
            post.setStock(stockService.findOne(postDto.getStockId()));
        }

        return postRepository.save(post);
    }

    public long count() {
        return count(null);
    }

    public long count(Predicate predicate) {
        return postRepository.count(predicate);
    }

    @Transactional(readOnly = false)
    public void delete(PostDto postDto) {
        Post post = postRepository.findOne(postDto.getId());
        if (Objects.isNull(post)) {
            throw new PostException("Not exist post!");
        }
        if (ObjectUtils.notEqual(postDto.getUserId(), post.getUser().getId())) {
            throw new PostException("Not allowed!");
        }
        postRepository.delete(post.getId());
    }

    @Transactional(readOnly = false)
    public void deleteAll() {
        postRepository.deleteAll();
    }
}
