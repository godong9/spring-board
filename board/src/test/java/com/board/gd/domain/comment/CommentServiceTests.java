package com.board.gd.domain.comment;

import com.board.gd.TestHelper;
import com.board.gd.domain.post.Post;
import com.board.gd.domain.post.PostDto;
import com.board.gd.domain.post.PostService;
import com.board.gd.domain.user.User;
import com.board.gd.domain.user.UserService;
import com.board.gd.exception.CommentException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by gd.godong9 on 2017. 4. 12.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CommentServiceTests {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Test
    public void success_findByPostId() {
        // given
        User testUser = userService.create(TestHelper.getTestUserDto("test"));
        PostDto testPostDto = TestHelper.getTestPostDto(testUser.getId());
        Post testPost = postService.create(testPostDto);
        CommentDto commentDto = TestHelper.getTestCommentDto(testUser.getId(), testPost.getId());
        commentService.create(commentDto);

        // when
        List<Comment> comments = commentService.findByPostId(testPost.getId());

        // then
        assertEquals(comments.size(), 1);
        Comment comment1 = comments.get(0);
        TestHelper.assertCommentDtoAndComment(commentDto, comment1);
    }

    @Test(expected = CommentException.class)
    public void fail_create_when_invalid_postId() {
        // given
        User testUser = userService.create(TestHelper.getTestUserDto("test"));
        CommentDto commentDto = TestHelper.getTestCommentDto(testUser.getId(), -1L);

        // when
        commentService.create(commentDto);
    }

    @Test
    public void success_create() {
        // given
        User testUser = userService.create(TestHelper.getTestUserDto("test"));
        PostDto testPostDto = TestHelper.getTestPostDto(testUser.getId());
        Post testPost = postService.create(testPostDto);
        CommentDto commentDto = TestHelper.getTestCommentDto(testUser.getId(), testPost.getId());

        // when
        Comment testComment = commentService.create(commentDto);

        // then
        TestHelper.assertCommentDtoAndComment(commentDto, testComment);
    }
}
