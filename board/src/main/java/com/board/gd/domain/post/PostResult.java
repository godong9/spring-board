package com.board.gd.domain.post;

import com.board.gd.domain.user.User;
import com.board.gd.domain.user.UserResult;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gd.godong9 on 2017. 4. 10.
 */

@Data
public class PostResult {
    private Long id;
    private String title;
    private String content;
    private Long viewCount;
    private UserResult user;
    private Long commentCount;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private Date createdAt;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private Date updatedAt;

    public static PostResult getPostResult(Post post) {
        UserResult userResult = new UserResult();
        User user = post.getUser();
        userResult.setId(user.getId());
        userResult.setName(user.getName());

        PostResult postResult = new PostResult();
        postResult.setId(post.getId());
        postResult.setUser(userResult);
        postResult.setTitle(post.getTitle());
        postResult.setContent(post.getContent());
        postResult.setCommentCount(post.getCommentCount());
        postResult.setViewCount(post.getViewCount());
        postResult.setCreatedAt(post.getCreatedAt());
        postResult.setUpdatedAt(post.getUpdatedAt());

        return postResult;
    }

    public static List<PostResult> getPostResultList(Page<Post> posts) {
        List<Post> postList = posts.getContent();
        return postList.stream()
                .map(post -> getPostResult(post))
                .collect(Collectors.toList());
    }
}
