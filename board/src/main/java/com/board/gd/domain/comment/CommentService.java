package com.board.gd.domain.comment;

import com.board.gd.domain.post.Post;
import com.board.gd.domain.post.PostService;
import com.board.gd.domain.user.User;
import com.board.gd.domain.user.UserService;
import com.board.gd.exception.CommentException;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * Created by gd.godong9 on 2017. 4. 12.
 */

@Transactional(readOnly = true)
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    public Comment findOne(Long id) {
        return commentRepository.findOne(id);
    }

    public List<Comment> findByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public Page<Comment> findAll(Predicate predicate, Pageable pageable) {
        if (Objects.isNull(pageable)) {
            throw new CommentException("Invalid pageable!");
        }
        return commentRepository.findAll(predicate, pageable);
    }

    @Transactional(readOnly = false)
    public Comment create(CommentDto commentDto) {
        User user = userService.findOne(commentDto.getUserId());
        Post post = postService.findOne(commentDto.getPostId());
        if (Objects.isNull(user) || Objects.isNull(post)) {
            throw new CommentException("Not exist user or post!");
        }
        postService.increaseCommentCount(post);
        return commentRepository.save(Comment.builder()
                .content(commentDto.getContent())
                .post(post)
                .user(user)
                .build());
    }

    @Transactional(readOnly = false)
    public Comment update(CommentDto commentDto) {
        Comment comment = commentRepository.findOne(commentDto.getId());
        if (ObjectUtils.notEqual(commentDto.getUserId(), comment.getUser().getId())) {
            throw new CommentException("Not allowed!");
        }

        if (!Objects.isNull(commentDto.getContent())) {
            comment.setContent(commentDto.getContent());
        }

        return commentRepository.save(comment);
    }

    public long count() {
        return count(null);
    }

    public long count(Predicate predicate) {
        return commentRepository.count(predicate);
    }

    @Transactional(readOnly = false)
    public void delete(CommentDto commentDto) {
        Comment comment = commentRepository.findOne(commentDto.getId());
        if (Objects.isNull(comment)) {
            throw new CommentException("Not exist comment!");
        }
        if (ObjectUtils.notEqual(commentDto.getUserId(), comment.getUser().getId())) {
            throw new CommentException("Not allowed!");
        }
        commentRepository.delete(comment.getId());
    }

    @Transactional(readOnly = false)
    public void deleteAll() {
        commentRepository.deleteAll();
    }
}
