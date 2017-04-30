package com.board.gd.domain.comment;

import com.board.gd.TestHelper;
import com.board.gd.domain.post.Post;
import com.board.gd.domain.post.PostDto;
import com.board.gd.domain.post.PostService;
import com.board.gd.domain.user.User;
import com.board.gd.domain.user.UserService;
import com.board.gd.exception.CommentException;
import com.board.gd.mail.MailService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

    @MockBean
    private MailService mailService;

    @Before
    public void setUp() {
        commentService.deleteAll();
        postService.deleteAll();
        userService.deleteAll();
    }

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
        assertEquals(Math.toIntExact(testComment.getPost().getCommentCount()), 1);
    }

    @Test(expected = CommentException.class)
    public void fail_update_when_invalid_userId() {
        // given
        User testUser = userService.create(TestHelper.getTestUserDto("test"));
        PostDto testPostDto = TestHelper.getTestPostDto(testUser.getId());
        Post testPost = postService.create(testPostDto);
        CommentDto commentDto = TestHelper.getTestCommentDto(testUser.getId(), testPost.getId());
        Comment testComment = commentService.create(commentDto);

        String changedContent = "changed content";
        CommentDto changedCommentDto = new CommentDto();
        changedCommentDto.setId(testComment.getId());
        changedCommentDto.setPostId(testPost.getId());
        changedCommentDto.setUserId(-1L);
        changedCommentDto.setContent(changedContent);

        // when
        commentService.update(changedCommentDto);
    }

    @Test
    public void success_update() {
        // given
        User testUser = userService.create(TestHelper.getTestUserDto("test"));
        PostDto testPostDto = TestHelper.getTestPostDto(testUser.getId());
        Post testPost = postService.create(testPostDto);
        CommentDto commentDto = TestHelper.getTestCommentDto(testUser.getId(), testPost.getId());
        Comment testComment = commentService.create(commentDto);

        String changedContent = "changed content";
        CommentDto changedCommentDto = new CommentDto();
        changedCommentDto.setId(testComment.getId());
        changedCommentDto.setPostId(testPost.getId());
        changedCommentDto.setUserId(testUser.getId());
        changedCommentDto.setContent(changedContent);

        // when
        Comment changedComment = commentService.update(changedCommentDto);

        // then
        assertEquals(testComment.getId(), changedComment.getId());
        assertEquals(changedContent, changedComment.getContent());
    }

    @Test(expected = CommentException.class)
    public void fail_delete_when_invalid_id() {
        // given
        CommentDto deleteDto = new CommentDto();
        deleteDto.setId(-1L);

        // when
        commentService.delete(deleteDto);
    }

    @Test
    public void success_delete() {
        // given
        User testUser = userService.create(TestHelper.getTestUserDto("test"));
        PostDto testPostDto = TestHelper.getTestPostDto(testUser.getId());
        Post testPost = postService.create(testPostDto);
        CommentDto commentDto = TestHelper.getTestCommentDto(testUser.getId(), testPost.getId());
        Comment testComment = commentService.create(commentDto);

        Long deletedId = testComment.getId();

        CommentDto deleteDto = new CommentDto();
        deleteDto.setId(deletedId);
        deleteDto.setUserId(testUser.getId());

        // when
        commentService.delete(deleteDto);

        // then
        assertEquals(null, commentService.findOne(deletedId));
    }
}
