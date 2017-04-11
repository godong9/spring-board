package com.board.gd.domain.post;

import com.board.gd.exception.PostException;
import com.board.gd.domain.user.User;
import com.board.gd.domain.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.types.Predicate;

import java.util.List;

import static org.junit.Assert.assertEquals;
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

    @Autowired
    private PostTestHelper postTestHelper;

    @Before
    public void setUp() {
        postService.deleteAll();
        userService.deleteAll();
    }

    @Test
    public void fail_findOne_when_invalid_id() {
        // given
        User testUser = postTestHelper.saveAndGetTestUser("test");
        PostDto testPostDto = postTestHelper.getTestPostDto(testUser.getId());
        postService.save(testPostDto);

        // when
        Post post = postService.findOne(-1L);

        // then
        assertEquals(post, null);
    }

    @Test
    public void success_findOne() {
        // given
        User testUser = postTestHelper.saveAndGetTestUser("test");
        PostDto testPostDto = postTestHelper.getTestPostDto(testUser.getId());
        Post testPost = postService.save(testPostDto);

        // when
        Post post = postService.findOne(testPost.getId());

        // then
        postTestHelper.assertPostDtoAndPost(testPostDto, post);
    }

    @Test(expected = PostException.class)
    public void fail_findAll_when_invalid_pageable() {
        // given
        User testUser = postTestHelper.saveAndGetTestUser("test");
        PostDto testPostDto1 = postTestHelper.getTestPostDto(testUser.getId());
        PostDto testPostDto2 = postTestHelper.getTestPostDto(testUser.getId());
        postService.save(testPostDto1);
        postService.save(testPostDto2);

        // when
        Page<Post> result = postService.findAll(null, null);
    }

    @Test
    public void success_findAll_sortBy_id_desc() {
        // given
        User testUser = postTestHelper.saveAndGetTestUser("test");
        PostDto testPostDto1 = postTestHelper.getTestPostDto(testUser.getId());
        PostDto testPostDto2 = postTestHelper.getTestPostDto(testUser.getId());
        PostDto testPostDto3 = postTestHelper.getTestPostDto(testUser.getId());
        postService.save(testPostDto1);
        postService.save(testPostDto2);
        postService.save(testPostDto3);
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
        postTestHelper.assertPostDtoAndPost(testPostDto3, post3);
        postTestHelper.assertPostDtoAndPost(testPostDto2, post2);
        postTestHelper.assertPostDtoAndPost(testPostDto1, post1);
    }

    @Test
    public void success_findAll_sortBy_createdAt_desc() {
        // given
        User testUser = postTestHelper.saveAndGetTestUser("test");
        PostDto testPostDto1 = postTestHelper.getTestPostDto(testUser.getId());
        PostDto testPostDto2 = postTestHelper.getTestPostDto(testUser.getId());
        PostDto testPostDto3 = postTestHelper.getTestPostDto(testUser.getId());
        postService.save(testPostDto1);
        postService.save(testPostDto2);
        postService.save(testPostDto3);
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
        postTestHelper.assertPostDtoAndPost(testPostDto3, post3);
        postTestHelper.assertPostDtoAndPost(testPostDto2, post2);
        postTestHelper.assertPostDtoAndPost(testPostDto1, post1);
    }

    @Test
    public void success_findAll_size_1_sortBy_createdAt_asc() {
        // given
        User testUser = postTestHelper.saveAndGetTestUser("test");
        PostDto testPostDto1 = postTestHelper.getTestPostDto(testUser.getId());
        PostDto testPostDto2 = postTestHelper.getTestPostDto(testUser.getId());
        postService.save(testPostDto1);
        postService.save(testPostDto2);
        Pageable pageRequest = new PageRequest(0, 1, new Sort(Sort.Direction.ASC, "createdAt"));
        Predicate predicateMock = mock(Predicate.class);

        // when
        Page<Post> result = postService.findAll(predicateMock, pageRequest);
        List<Post> posts = result.getContent();

        // then
        assertEquals(posts.size(), 1);
        Post post1 = posts.get(0);
        postTestHelper.assertPostDtoAndPost(testPostDto1, post1);
    }

    @Test(expected = PostException.class)
    public void fail_save_insert() {
        // given
        PostDto testPostDto = postTestHelper.getTestPostDto(-1L);

        // when
        Post post = postService.save(testPostDto);

        // then
        postTestHelper.assertPostDtoAndPost(testPostDto, post);
    }

    @Test
    public void success_save_insert() {
        // given
        User testUser = postTestHelper.saveAndGetTestUser("test");
        PostDto testPostDto = postTestHelper.getTestPostDto(testUser.getId());

        // when
        Post post = postService.save(testPostDto);

        // then
        postTestHelper.assertPostDtoAndPost(testPostDto, post);
    }

    @Test
    public void success_save_update() {
        // given
        User testUser = postTestHelper.saveAndGetTestUser("test");
        PostDto testPostDto = postTestHelper.getTestPostDto(testUser.getId());
        Post post = postService.save(testPostDto);

        // when
        String changedContent = "change content";
        testPostDto.setId(post.getId());
        testPostDto.setContent(changedContent);
        Post changedPost = postService.save(testPostDto);

        // then
        assertEquals(post.getId(), changedPost.getId());
        assertEquals(changedPost.getContent(), changedContent);
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
        User testUser = postTestHelper.saveAndGetTestUser("test");
        PostDto testPostDto1 = postTestHelper.getTestPostDto(testUser.getId());
        PostDto testPostDto2 = postTestHelper.getTestPostDto(testUser.getId());
        postService.save(testPostDto1);
        postService.save(testPostDto2);

        // when
        Long postCount = postService.count();

        // then
        assertEquals(Math.toIntExact(postCount), 2);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void fail_delete_when_invalid_id() {
        // given
        Long deletedId = -1L;

        // when
        postService.delete(deletedId);
    }

    @Test
    public void success_delete() {
        // given
        User testUser = postTestHelper.saveAndGetTestUser("test");
        PostDto testPostDto = postTestHelper.getTestPostDto(testUser.getId());
        Post post = postService.save(testPostDto);
        Long deletedId = post.getId();

        // when
        postService.delete(deletedId);

        // then
        assertEquals(postService.findOne(deletedId), null);
    }


}
