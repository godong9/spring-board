package com.board.gd.domain.comment;

import com.board.gd.domain.post.Post;
import com.board.gd.domain.post.PostService;
import com.board.gd.domain.user.User;
import com.board.gd.domain.user.UserService;
import com.board.gd.exception.CommentException;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Comment> findByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    @Transactional(readOnly = false)
    public Comment create(CommentDto commentDto) {
        User user = userService.findOne(commentDto.getUserId());
        Post post = postService.findOne(commentDto.getPostId());
        if (Objects.isNull(user) || Objects.isNull(post)) {
            throw new CommentException("Not exist user or post!");
        }
        return commentRepository.save(Comment.builder()
                .content(commentDto.getContent())
                .post(post)
                .user(user)
                .build());
    }
}
