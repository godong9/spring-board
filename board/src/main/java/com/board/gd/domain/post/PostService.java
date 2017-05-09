package com.board.gd.domain.post;

/**
 * Created by gd.godong9 on 2017. 4. 4.
 */

import com.board.gd.domain.board.Board;
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

import java.util.Objects;

@Transactional(readOnly = true)
@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostLikeRepository postLikeRepository;

    @Autowired
    private UserService userService;

    public Post findOne(Long id) {
        return findOne(id, null);
    }

    public Post findOne(Long id, Long userId) {
        Post post = postRepository.findOne(id);
        if (Objects.isNull(userId)) {
            return post;
        }
        PostLike postLike = postLikeRepository.findByPostIdAndUserId(id, userId);
        if (!Objects.isNull(postLike)) {
            post.setIsLiked(true);
        }
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

        Board board = null;
        if (!Objects.isNull(postDto.getBoardId())) {
            board = Board.builder()
                    .id(postDto.getBoardId()) // 게시판 ID는 validation 체크 제외
                    .build();
        }

        return postRepository.save(Post.builder()
                .type(PostType.FREE) // 디폴트로 FREE로 세팅
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .viewCount(0L)
                .commentCount(0L)
                .postLikeCount(0L)
                .blocked(false)
                .user(user)
                .board(board)
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

        increasePostLikeCount(post);

        return postLikeRepository.save(PostLike.builder()
                .post(post)
                .user(user)
                .build());
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

        if (!Objects.isNull(postDto.getBoardId())) {
            post.setBoard(Board.builder()
                    .id(postDto.getBoardId()) // 게시판 ID는 validation 체크 제외
                    .build());
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
