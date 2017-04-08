package com.board.gd.post;

import com.board.gd.exception.PostException;
import com.board.gd.user.User;
import com.board.gd.user.UserDto;
import com.board.gd.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

    @Before
    public void setUp() {
        postService.deleteAll();
        userService.deleteAll();
    }

    @Test
    public void fail_findOne_when_invalid_id() {
        // given
        User testUser = saveAndGetTestUser();
        PostDto testPostDto = getTestPostDto(testUser.getId());
        postService.save(testPostDto);

        // when
        Post post = postService.findOne(-1L);

        // then
        assertEquals(post, null);
    }

    @Test
    public void success_findOne() {
        // given
        User testUser = saveAndGetTestUser();
        PostDto testPostDto = getTestPostDto(testUser.getId());
        Post testPost = postService.save(testPostDto);

        // when
        Post post = postService.findOne(testPost.getId());

        // then
        assertPostDtoAndPost(testPostDto, post);
    }

    @Test(expected = PostException.class)
    public void fail_save_insert() {
        // given
        PostDto testPostDto = getTestPostDto(-1L);

        // when
        Post post = postService.save(testPostDto);

        // then
        assertPostDtoAndPost(testPostDto, post);
    }

    @Test
    public void success_save_insert() {
        // given
        User testUser = saveAndGetTestUser();
        PostDto testPostDto = getTestPostDto(testUser.getId());

        // when
        Post post = postService.save(testPostDto);

        // then
        assertPostDtoAndPost(testPostDto, post);
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
        User testUser = saveAndGetTestUser();
        PostDto testPostDto1 = getTestPostDto(testUser.getId());
        PostDto testPostDto2 = getTestPostDto(testUser.getId());
        postService.save(testPostDto1);
        postService.save(testPostDto2);

        // when
        Long postCount = postService.count();

        // then
        assertEquals(Math.toIntExact(postCount), 2);
    }


    public User saveAndGetTestUser() {
        UserDto userDto = new UserDto();
        userDto.setName("test");
        userDto.setEmail("test@test.com");
        userDto.setPassword("test");
        userDto.setProfileImg("http://test.com/test.jpg");
        User testUser = userService.save(userDto);
        return testUser;
    }

    public PostDto getTestPostDto(Long userId) {
        PostDto postDto = new PostDto();
        postDto.setType(PostType.FREE);
        postDto.setTitle("test title");
        postDto.setContent("test content");
        postDto.setUserId(userId);
        return postDto;
    }

    public void assertPostDtoAndPost(PostDto postDto, Post post) {
        assertEquals(postDto.getType(), post.getType());
        assertEquals(postDto.getTitle(), post.getTitle());
        assertEquals(postDto.getContent(), post.getContent());
        assertEquals(postDto.getUserId(), post.getUser().getId());
        assertNotNull(post.getCreatedAt());
        assertNotNull(post.getUpdatedAt());
    }

}
