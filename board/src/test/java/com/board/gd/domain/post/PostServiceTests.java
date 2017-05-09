package com.board.gd.domain.post;

import com.board.gd.TestHelper;
import com.board.gd.domain.user.User;
import com.board.gd.domain.user.UserService;
import com.board.gd.exception.PostException;
import com.board.gd.mail.MailService;
import com.querydsl.core.types.Predicate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by gd.godong9 on 2017. 4. 3.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PostServiceTests {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @MockBean
    private MailService mailService;

    @Before
    public void setUp() {
        postService.deleteAll();
        userService.deleteAll();
    }

    @Test
    public void fail_findOne_when_invalid_id() {
        // given
        User testUser = userService.create(TestHelper.getTestUserDto("test"));
        PostDto testPostDto = TestHelper.getTestPostDto(testUser.getId());
        postService.create(testPostDto);

        // when
        Post post = postService.findOne(-1L);

        // then
        assertEquals(post, null);
    }

    @Test
    public void success_findOne() {
        // given
        User testUser = userService.create(TestHelper.getTestUserDto("test"));
        PostDto testPostDto = TestHelper.getTestPostDto(testUser.getId());
        Post testPost = postService.create(testPostDto);

        // when
        Post post = postService.findOne(testPost.getId());

        // then
        TestHelper.assertPostDtoAndPost(testPostDto, post);
    }

    @Test
    public void success_findOne_when_user_isLiked() {
        // given
        User testUser = userService.create(TestHelper.getTestUserDto("test"));
        PostDto testPostDto = TestHelper.getTestPostDto(testUser.getId());
        Post testPost = postService.create(testPostDto);

        PostLikeDto testPostLikeDto = new PostLikeDto();
        testPostLikeDto.setPostId(testPost.getId());
        testPostLikeDto.setUserId(testPost.getUser().getId());
        testPostLikeDto.setUnlike(false);
        postService.createPostLike(testPostLikeDto);

        // when
        Post afterTestPost = postService.findOne(testPost.getId(), testPost.getUser().getId());

        // then
        TestHelper.assertPostDtoAndPost(testPostDto, afterTestPost);
        assertThat(afterTestPost.getIsLiked(), is(true));
        assertThat(afterTestPost.getPostLikeCount(), is(1L));
    }

    @Test
    public void success_increaseViewCountAndFindOne() {
        // given
        User testUser = userService.create(TestHelper.getTestUserDto("test"));
        PostDto testPostDto = TestHelper.getTestPostDto(testUser.getId());
        Post testPost = postService.create(testPostDto);

        // when
        postService.increaseViewCountAndFindOne(testPost.getId());
        Post post = postService.increaseViewCountAndFindOne(testPost.getId());

        // then
        TestHelper.assertPostDtoAndPost(testPostDto, post);
        assertEquals(Math.toIntExact(post.getViewCount()), 2);
    }

    @Test(expected = PostException.class)
    public void fail_findAll_when_invalid_pageable() {
        // given
        User testUser = userService.create(TestHelper.getTestUserDto("test"));
        PostDto testPostDto1 = TestHelper.getTestPostDto(testUser.getId());
        PostDto testPostDto2 = TestHelper.getTestPostDto(testUser.getId());
        postService.create(testPostDto1);
        postService.create(testPostDto2);

        // when
        Page<Post> result = postService.findAll(null, null);
    }

    @Test
    public void success_findAll_sortBy_id_desc() {
        // given
        User testUser = userService.create(TestHelper.getTestUserDto("test"));
        PostDto testPostDto1 = TestHelper.getTestPostDto(testUser.getId());
        PostDto testPostDto2 = TestHelper.getTestPostDto(testUser.getId());
        PostDto testPostDto3 = TestHelper.getTestPostDto(testUser.getId());
        postService.create(testPostDto1);
        postService.create(testPostDto2);
        postService.create(testPostDto3);
        Pageable pageRequest = new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "id"));
        Predicate predicateMock = mock(Predicate.class);

        // when
        Page<Post> result = postService.findAll(predicateMock, pageRequest);
        List<Post> posts = result.getContent();

        // then
        assertEquals(posts.size(), 3);
        Post post3 = posts.get(0);
        Post post2 = posts.get(1);
        Post post1 = posts.get(2);
        TestHelper.assertPostDtoAndPost(testPostDto3, post3);
        TestHelper.assertPostDtoAndPost(testPostDto2, post2);
        TestHelper.assertPostDtoAndPost(testPostDto1, post1);
    }

    @Test
    public void success_findAll_sortBy_createdAt_desc() {
        // given
        User testUser = userService.create(TestHelper.getTestUserDto("test"));
        PostDto testPostDto1 = TestHelper.getTestPostDto(testUser.getId());
        PostDto testPostDto2 = TestHelper.getTestPostDto(testUser.getId());
        PostDto testPostDto3 = TestHelper.getTestPostDto(testUser.getId());
        postService.create(testPostDto1);
        postService.create(testPostDto2);
        postService.create(testPostDto3);
        Pageable pageRequest = new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "createdAt"));
        Predicate predicateMock = mock(Predicate.class);

        // when
        Page<Post> result = postService.findAll(predicateMock, pageRequest);
        List<Post> posts = result.getContent();

        // then
        assertEquals(posts.size(), 3);
        Post post3 = posts.get(0);
        Post post2 = posts.get(1);
        Post post1 = posts.get(2);
        TestHelper.assertPostDtoAndPost(testPostDto3, post3);
        TestHelper.assertPostDtoAndPost(testPostDto2, post2);
        TestHelper.assertPostDtoAndPost(testPostDto1, post1);
    }

    @Test
    public void success_findAll_size_1_sortBy_createdAt_asc() {
        // given
        User testUser = userService.create(TestHelper.getTestUserDto("test"));
        PostDto testPostDto1 = TestHelper.getTestPostDto(testUser.getId());
        PostDto testPostDto2 = TestHelper.getTestPostDto(testUser.getId());
        postService.create(testPostDto1);
        postService.create(testPostDto2);
        Pageable pageRequest = new PageRequest(0, 1, new Sort(Sort.Direction.ASC, "createdAt"));
        Predicate predicateMock = mock(Predicate.class);

        // when
        Page<Post> result = postService.findAll(predicateMock, pageRequest);
        List<Post> posts = result.getContent();

        // then
        assertEquals(posts.size(), 1);
        Post post1 = posts.get(0);
        TestHelper.assertPostDtoAndPost(testPostDto1, post1);
    }

    @Test(expected = PostException.class)
    public void fail_create() {
        // given
        PostDto testPostDto = TestHelper.getTestPostDto(-1L);

        // when
        Post post = postService.create(testPostDto);

        // then
        TestHelper.assertPostDtoAndPost(testPostDto, post);
    }

    @Test
    public void success_create() {
        // given
        User testUser = userService.create(TestHelper.getTestUserDto("test"));
        PostDto testPostDto = TestHelper.getTestPostDto(testUser.getId());

        // when
        Post post = postService.create(testPostDto);

        // then
        TestHelper.assertPostDtoAndPost(testPostDto, post);
        assertEquals(post.getBlocked(), false);
    }

    @Test
    public void success_create_when_boardId_exist() {
        // given
        User testUser = userService.create(TestHelper.getTestUserDto("test"));
        PostDto testPostDto = TestHelper.getTestPostDto(testUser.getId());
        testPostDto.setBoardId(1L);

        // when
        Post post = postService.create(testPostDto);

        // then
        TestHelper.assertPostDtoAndPost(testPostDto, post);
        assertEquals(testPostDto.getBoardId(), post.getBoard().getId());
    }

    @Test
    public void success_createPostLike_when_unliked_false() {
        // given
        User testUser = userService.create(TestHelper.getTestUserDto("test"));
        Post testPost = postService.create(TestHelper.getTestPostDto(testUser.getId()));
        PostLikeDto testPostLikeDto = TestHelper.getTestPostLikeDto(testUser.getId(), testPost.getId(), false);

        // when
        PostLike postLike = postService.createPostLike(testPostLikeDto);

        // then
        TestHelper.assertPostLikeDtoAndPostLike(testPostLikeDto, postLike);
        Post afterPost = postService.findOne(testPost.getId());
        assertEquals(Math.toIntExact(afterPost.getPostLikeCount()), 1);
    }

    @Test
    public void success_createPostLike_when_unliked_true() {
        // given

        //TODO

        // when

        // then

    }

    @Test
    public void fail_createPostLike_when_alreadyLiked() {
        // given

        // when

        // then
    }

    @Test
    public void fail_createPostLike_when_alreadyLiked_and_notUnliked() {
        // given

        // when

        // then
    }

    @Test(expected = PostException.class)
    public void fail_update_when_invalid_userId() {
        // given
        User testUser = userService.create(TestHelper.getTestUserDto("test"));
        PostDto testPostDto = TestHelper.getTestPostDto(testUser.getId());
        Post post = postService.create(testPostDto);
        String changedTitle = "changed title";
        PostDto changedPostDto = new PostDto();
        changedPostDto.setId(post.getId());
        changedPostDto.setUserId(-1L);
        changedPostDto.setTitle(changedTitle);

        // when
        postService.update(changedPostDto);
    }

    @Test
    public void success_update() {
        // given
        User testUser = userService.create(TestHelper.getTestUserDto("test"));
        PostDto testPostDto = TestHelper.getTestPostDto(testUser.getId());
        Post post = postService.create(testPostDto);

        String changedTitle = "changed title";
        PostDto changedPostDto = new PostDto();
        changedPostDto.setId(post.getId());
        changedPostDto.setUserId(testUser.getId());
        changedPostDto.setTitle(changedTitle);
        changedPostDto.setBoardId(1L);

        // when
        Post changedPost = postService.update(changedPostDto);

        // then
        assertEquals(post.getId(), changedPost.getId());
        assertEquals(changedTitle, changedPost.getTitle());
        assertEquals(changedPostDto.getBoardId(), changedPost.getBoard().getId());
        assertEquals(testPostDto.getContent(), changedPost.getContent());
    }

    @Test
    public void success_count_0() {
        // given

        // when
        Long postCount = postService.count();

        // then
        assertEquals(Math.toIntExact(postCount), 0);
    }

    @Test
    public void success_count_2() {
        // given
        User testUser = userService.create(TestHelper.getTestUserDto("test"));
        PostDto testPostDto1 = TestHelper.getTestPostDto(testUser.getId());
        PostDto testPostDto2 = TestHelper.getTestPostDto(testUser.getId());
        postService.create(testPostDto1);
        postService.create(testPostDto2);

        // when
        Long postCount = postService.count();

        // then
        assertEquals(Math.toIntExact(postCount), 2);
    }

    @Test(expected = PostException.class)
    public void fail_delete_when_invalid_id() {
        // given
        PostDto deleteDto = new PostDto();
        deleteDto.setId(-1L);

        // when
        postService.delete(deleteDto);
    }

    @Test
    public void success_delete() {
        // given
        User testUser = userService.create(TestHelper.getTestUserDto("test"));
        PostDto testPostDto = TestHelper.getTestPostDto(testUser.getId());
        Post post = postService.create(testPostDto);
        Long deletedId = post.getId();

        PostDto deleteDto = new PostDto();
        deleteDto.setId(deletedId);
        deleteDto.setUserId(testUser.getId());

        // when
        postService.delete(deleteDto);

        // then
        assertEquals(postService.findOne(deletedId), null);
    }

}
