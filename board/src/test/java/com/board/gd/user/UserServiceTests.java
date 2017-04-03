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
        UserDao testUserDao = userService.save(testUserDto);

        // when
        UserDao userDao = userService.findOne(testUserDao.getId());

        // then
        assertUserDtoAndUserDao(testUserDto, userDao);
    }

    @Test
    public void success_save_insert_with_password() {
        // given
        UserDto testUserDto = getTestUserDto();

        // when
        UserDao testUserDao = userService.save(testUserDto);

        // then
        assertUserDtoAndUserDao(testUserDto, testUserDao);
    }

    @Test
    public void success_save_insert_with_fbId() {
        // given
        UserDto testUserDto = getTestUserDto();
        testUserDto.setPassword(null);
        testUserDto.setFbId("fbtest");

        // when
        UserDao testUserDao = userService.save(testUserDto);

        // then
        assertUserDtoAndUserDao(testUserDto, testUserDao);
    }

    @Test
    public void success_save_update() {
        // given
        UserDto testUserDto = getTestUserDto();
        String changedName = "change";

        // when
        UserDao testUserDao = userService.save(testUserDto);
        testUserDto.setId(testUserDao.getId());
        testUserDto.setName(changedName);
        UserDao changedTestUserDao = userService.save(testUserDto);

        // then
        assertEquals(testUserDao.getId(), changedTestUserDao.getId());
        assertEquals(changedTestUserDao.getName(), changedName);
    }

    @Test
    public void success_findAll() {
        // given
        UserDto testUserDto1 = getTestUserDto();
        UserDto testUserDto2 = getTestUserDto();
        userService.save(testUserDto1);
        userService.save(testUserDto2);

        // when
        List<UserDao> testUserDaoList = userService.findAll();

        // then
        assertEquals(testUserDaoList.size(), 2);
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
        List<UserDao> testUserDaoList = userService.findAll();
        assertEquals(testUserDaoList.size(), 0);
    }

    public void assertUserDtoAndUserDao(UserDto userDto, UserDao userDao) {
        assertEquals(userDto.getName(), userDao.getName());
        assertEquals(userDto.getEmail(), userDao.getEmail());
        assertEquals(userDto.getPassword(), userDao.getPassword());
        assertEquals(userDto.getProfileImg(), userDao.getProfileImg());
        assertEquals(userDto.getFbId(), userDao.getFbId());
        assertNotNull(userDao.getCreatedAt());
        assertNotNull(userDao.getUpdatedAt());
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
