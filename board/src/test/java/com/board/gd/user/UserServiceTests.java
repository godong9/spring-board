package com.board.gd.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by gd.godong9 on 2017. 4. 3.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserService userService;

    @Before
    public void setUp() {
        userService.deleteAll();
    }

    @Test
    public void success_findOne() {
        // given
        UserDto testUserDto = getTestUserDto();
        User testUser = userService.save(testUserDto);

        // when
        User user = userService.findOne(testUser.getId());

        // then
        assertUserDtoAndUserDao(testUserDto, user);
    }

    @Test
    public void fail_findOne() {
        // given
        UserDto testUserDto = getTestUserDto();
        User testUser = userService.save(testUserDto);

        // when
        User user = userService.findOne(-1L);

        // then
        assertEquals(user, null);
    }


    @Test
    public void success_save_insert_with_password() {
        // given
        UserDto testUserDto = getTestUserDto();

        // when
        User testUser = userService.save(testUserDto);

        // then
        assertUserDtoAndUserDao(testUserDto, testUser);
    }

    @Test
    public void success_save_insert_with_fbId() {
        // given
        UserDto testUserDto = getTestUserDto();
        testUserDto.setPassword(null);
        testUserDto.setFbId("fbtest");

        // when
        User testUser = userService.save(testUserDto);

        // then
        assertUserDtoAndUserDao(testUserDto, testUser);
    }

    @Test
    public void success_save_update() {
        // given
        UserDto testUserDto = getTestUserDto();
        String changedName = "change";

        // when
        User testUser = userService.save(testUserDto);
        testUserDto.setId(testUser.getId());
        testUserDto.setName(changedName);
        User changedTestUser = userService.save(testUserDto);

        // then
        assertEquals(testUser.getId(), changedTestUser.getId());
        assertEquals(changedTestUser.getName(), changedName);
    }

    @Test
    public void success_count_0() {
        // given

        // when
        Long userCount = userService.count();

        // then
        assertEquals(Math.toIntExact(userCount), 0);
    }

    @Test
    public void success_count_2() {
        // given
        UserDto testUserDto1 = getTestUserDto();
        UserDto testUserDto2 = getTestUserDto();
        userService.save(testUserDto1);
        userService.save(testUserDto2);

        // when
        Long userCount = userService.count();

        // then
        assertEquals(Math.toIntExact(userCount), 2);
    }

    @Test
    public void success_findAll() {
        // given
        UserDto testUserDto1 = getTestUserDto();
        UserDto testUserDto2 = getTestUserDto();
        userService.save(testUserDto1);
        userService.save(testUserDto2);

        // when
        List<User> testUserList = userService.findAll();

        // then
        assertEquals(testUserList.size(), 2);
    }

    @Test
    public void success_deleteAll() {
        // given
        UserDto testUserDto1 = getTestUserDto();
        UserDto testUserDto2 = getTestUserDto();
        userService.save(testUserDto1);
        userService.save(testUserDto2);

        // when
        userService.deleteAll();

        // then
        List<User> testUserList = userService.findAll();
        assertEquals(testUserList.size(), 0);
    }

    public void assertUserDtoAndUserDao(UserDto userDto, User user) {
        assertEquals(userDto.getName(), user.getName());
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getPassword(), user.getPassword());
        assertEquals(userDto.getProfileImg(), user.getProfileImg());
        assertEquals(userDto.getFbId(), user.getFbId());
        assertNotNull(user.getCreatedAt());
        assertNotNull(user.getUpdatedAt());
    }

    public UserDto getTestUserDto() {
        UserDto testUser = new UserDto();
        testUser.setName("test");
        testUser.setEmail("test@test.com");
        testUser.setPassword("test");
        testUser.setProfileImg("http://test.com/test.jpg");
        return testUser;
    }
}
