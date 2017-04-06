package com.board.gd.post;

import com.board.gd.user.User;
import com.board.gd.user.UserDto;
import com.board.gd.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by gd.godong9 on 2017. 4. 3.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTests {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Before
    public void setUp() {
        postService.deleteAll();
    }

    @Test
    public void success_save_insert() {
        // given
        User testUser = getTestUser();
        PostDto testPostDto = getTestPostDto(testUser.getId());

        // when
        Post post = postService.save(testPostDto);

        // then
        assertPostDtoAndPost(testPostDto, post);
    }

    public User getTestUser() {
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
