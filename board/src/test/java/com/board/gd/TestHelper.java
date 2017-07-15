package com.board.gd;

import com.board.gd.domain.comment.Comment;
import com.board.gd.domain.comment.CommentDto;
import com.board.gd.domain.post.*;
import com.board.gd.domain.stock.Stock;
import com.board.gd.domain.stock.StockDto;
import com.board.gd.domain.user.User;
import com.board.gd.domain.user.UserDto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by gd.godong9 on 2017. 4. 10.
 */

public class TestHelper {
    public static User getTestUser(Long id) {
        return User.builder()
                .id(id)
                .name("test" + id.toString())
                .password("test")
                .email("test" + id.toString() + "@test.com")
                .authUUID("testuuid")
                .build();
    }

    public static Stock getTestStock(Long id) {
        return Stock.builder()
                .id(id)
                .name("testname" + id.toString())
                .code("00000" + id.toString())
                .build();
    }

    public static UserDto getTestUserDto(String name) {
        UserDto userDto = new UserDto();
        userDto.setName(name);
        userDto.setEmail(name + "@test.com");
        userDto.setPassword("test");
        return userDto;
    }

    public static PostDto getTestPostDto(Long userId) {
        return getTestPostDto(userId, null);
    }

    public static PostDto getTestPostDto(Long userId, Long stockId) {
        PostDto postDto = new PostDto();
        postDto.setType(PostType.FREE);
        postDto.setTitle("test title");
        postDto.setContent("test content");
        postDto.setUserId(userId);
        postDto.setStockId(stockId);
        return postDto;
    }

    public static CommentDto getTestCommentDto(Long userId, Long postId) {
        CommentDto commentDto = new CommentDto();
        commentDto.setContent("test content");
        commentDto.setUserId(userId);
        commentDto.setPostId(postId);
        return commentDto;
    }

    public static StockDto getTestStockDto(String name, String code) {
        StockDto stockDto = new StockDto();
        stockDto.setName(name);
        stockDto.setCode(code);
        return stockDto;
    }

    public static PostLikeDto getTestPostLikeDto(Long userId, Long postId, Boolean unlike) {
        PostLikeDto postLikeDto = new PostLikeDto();
        postLikeDto.setUserId(userId);
        postLikeDto.setPostId(postId);
        postLikeDto.setUnlike(unlike);
        return postLikeDto;
    }

    public static void assertUserDtoAndUser(UserDto userDto, User user) {
        assertEquals(userDto.getName(), user.getName());
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getProfileImg(), user.getProfileImg());
        assertEquals(user.getEnabled(), false);
        assertNotNull(user.getAuthUUID());
        assertNotNull(user.getCreatedAt());
        assertNotNull(user.getUpdatedAt());
    }

    public static void assertPostDtoAndPost(PostDto postDto, Post post) {
        assertEquals(postDto.getType(), post.getType());
        assertEquals(postDto.getTitle(), post.getTitle());
        assertEquals(postDto.getContent(), post.getContent());
        assertEquals(postDto.getUserId(), post.getUser().getId());
        assertNotNull(post.getId());
        assertNotNull(post.getViewCount());
        assertNotNull(post.getCommentCount());
        assertNotNull(post.getCreatedAt());
        assertNotNull(post.getUpdatedAt());
    }

    public static void assertCommentDtoAndComment(CommentDto commentDto, Comment comment) {
        assertEquals(commentDto.getContent(), comment.getContent());
        assertEquals(commentDto.getUserId(), comment.getUser().getId());
        assertEquals(commentDto.getPostId(), comment.getPost().getId());
        assertNotNull(comment.getId());
        assertNotNull(comment.getCreatedAt());
        assertNotNull(comment.getUpdatedAt());
    }

    public static void assertPostLikeDtoAndPostLike(PostLikeDto postLikeDto, PostLike postLike) {
        assertEquals(postLikeDto.getPostId(), postLike.getPost().getId());
        assertEquals(postLikeDto.getUserId(), postLike.getUser().getId());
        assertNotNull(postLike.getId());
        assertNotNull(postLike.getCreatedAt());
        assertNotNull(postLike.getUpdatedAt());
    }
}
