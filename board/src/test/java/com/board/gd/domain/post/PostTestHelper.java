package com.board.gd.domain.post;

import com.board.gd.domain.user.User;
import com.board.gd.domain.user.UserDto;
import com.board.gd.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by gd.godong9 on 2017. 4. 10.
 */

@SpringBootTest
@Component
public class PostTestHelper {
    @Autowired
    private UserService userService;

    public User saveAndGetTestUser(String name) {
        UserDto userDto = new UserDto();
        userDto.setName(name);
        userDto.setEmail(name + "@test.com");
        userDto.setPassword("test");
        return userService.save(userDto);
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
