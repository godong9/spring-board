package com.board.gd.domain.comment;

import com.board.gd.domain.user.User;
import com.board.gd.domain.user.UserResult;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by gd.godong9 on 2017. 4. 12.
 */

@Data
public class CommentResult {
    private Long id;
    private String content;
    private UserResult user;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private Date createdAt;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private Date updatedAt;

    public static CommentResult getCommentResult(Comment comment) {
        UserResult userResult = new UserResult();
        User user = comment.getUser();
        userResult.setId(user.getId());
        userResult.setName(user.getName());
        if (!Objects.isNull(user.getCompany())) {
            userResult.setCompanyName(user.getCompany().getCompanyName());
        }

        CommentResult commentResult = new CommentResult();
        commentResult.setId(comment.getId());
        commentResult.setContent(comment.getContent());
        commentResult.setUser(userResult);
        commentResult.setCreatedAt(comment.getCreatedAt());
        commentResult.setUpdatedAt(comment.getUpdatedAt());

        return commentResult;
    }

    public static List<CommentResult> getCommentResultList(Page<Comment> commentPage) {
        List<Comment> commentList = commentPage.getContent();
        return commentList.stream()
                .map(comment -> getCommentResult(comment))
                .collect(Collectors.toList());
    }
}
