package com.board.gd.domain.post;

import com.board.gd.domain.user.JsonUser;
import com.board.gd.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by godong9 on 2017. 4. 8..
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResult {
    private String status;
    private String message;
    private JsonPost post;
    private List<JsonPost> posts;

    public static PostResult from(Post post, String msg) {
        return PostResult.builder()
                .status(HttpStatus.OK.toString())
                .post(getJsonPost(post))
                .message(msg)
                .build();
    }

    public static PostResult from(Page<Post> posts, String msg) {
        List<Post> postList = posts.getContent();
        List<JsonPost> postLists = postList.stream()
                .map(post -> getJsonPost(post))
                .collect(Collectors.toList());

        return PostResult.builder()
                .status(HttpStatus.OK.toString())
                .posts(postLists)
                .message(msg)
                .build();
    }

    public static JsonPost getJsonPost(Post post) {
        JsonUser jsonUser = new JsonUser();
        User user = post.getUser();
        jsonUser.setId(user.getId());
        jsonUser.setName(user.getName());

        JsonPost jsonPost = new JsonPost();
        jsonPost.setId(post.getId());
        jsonPost.setUser(jsonUser);
        jsonPost.setTitle(post.getTitle());
        jsonPost.setContent(post.getContent());
        jsonPost.setViewCount(post.getViewCount());

        return jsonPost;
    }
}
