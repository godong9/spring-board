package com.board.gd.domain.post;

import com.board.gd.domain.stock.Stock;
import com.board.gd.domain.stock.StockResult;
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
 * Created by gd.godong9 on 2017. 4. 10.
 */

@Data
public class PostResult {
    private Long id;
    private String title;
    private String content;
    private Long viewCount;
    private UserResult user;
    private StockResult stock;
    private Long commentCount;
    private Long postLikeCount;
    private Long postUnlikeCount;
    private Boolean blocked;
    private Boolean isLiked;
    private Boolean isUnliked;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private Date createdAt;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private Date updatedAt;

    public static PostResult getPostResult(Post post) {
        UserResult userResult = new UserResult();
        User user = post.getUser();
        userResult.setId(user.getId());
        userResult.setName(user.getName());
        if (!Objects.isNull(user.getCompany())) {
            userResult.setCompanyName(user.getCompany().getCompanyName());
        }

        PostResult postResult = new PostResult();
        postResult.setId(post.getId());
        postResult.setUser(userResult);
        postResult.setTitle(post.getTitle());
        postResult.setContent(post.getContent());
        postResult.setCommentCount(post.getCommentCount());
        postResult.setPostLikeCount(post.getPostLikeCount());
        postResult.setPostUnlikeCount(post.getPostUnlikeCount());
        postResult.setViewCount(post.getViewCount());
        postResult.setBlocked(post.getBlocked());
        postResult.setIsLiked(post.getIsLiked());
        postResult.setIsUnliked(post.getIsUnliked());
        postResult.setCreatedAt(post.getCreatedAt());
        postResult.setUpdatedAt(post.getUpdatedAt());

        StockResult stockResult = new StockResult();
        Stock stock = post.getStock();
        if (!Objects.isNull(stock)) {
            stockResult.setId(stock.getId());
            stockResult.setName(stock.getName());
            stockResult.setCode(stock.getCode());
            postResult.setStock(stockResult);
        }

        return postResult;
    }

    public static List<PostResult> getPostResultList(Page<Post> postPage) {
        List<Post> postList = postPage.getContent();
        return postList.stream()
                .map(post -> getPostResult(post))
                .collect(Collectors.toList());
    }
}
