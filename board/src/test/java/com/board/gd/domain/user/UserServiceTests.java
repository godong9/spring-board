package com.board.gd.domain.user;

import com.board.gd.TestHelper;
import com.board.gd.exception.UserException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by gd.godong9 on 2017. 4. 3.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTests {
    @Autowired
    private UserService userService;

    @Before
    public void setUp() {
        userService.deleteAll();
    }

    @Test
    public void fail_findOne_when_invalid_id() {
        // given
        UserDto testUserDto = TestHelper.getTestUserDto("test1");
        userService.create(testUserDto);

        // when
        User user = userService.findOne(-1L);

        // then
        assertEquals(user, null);
    }

    @Test
    public void success_findOne() {
        // given
        UserDto testUserDto = TestHelper.getTestUserDto("test1");
        User testUser = userService.create(testUserDto);

        // when
        User user = userService.findOne(testUser.getId());

        // then
        assertUserDtoAndUser(testUserDto, user);
    }

    @Test
    public void fail_findByEmail() {
        // given
        UserDto testUserDto = TestHelper.getTestUserDto("test1");
        userService.create(testUserDto);

        // when
        User user = userService.findByEmail("test1@");

        // then
        assertEquals(user, null);
    }

    @Test
    public void success_findByEmail() {
        // given
        UserDto testUserDto = TestHelper.getTestUserDto("test1");
        User testUser = userService.create(testUserDto);

        // when
        User user = userService.findByEmail(testUser.getEmail());

        // then
        assertUserDtoAndUser(testUserDto, user);
    }

    @Test
    public void fail_getRolesByUserId() {
        // given

        // when
        List<GrantedAuthority> roles = userService.getRolesByUserId(-1L);

        // then
        assertEquals(roles.size(), 0);
    }

    @Test
    public void success_getRolesByUserId() {
        // given
        UserDto testUserDto = TestHelper.getTestUserDto("test1");
        User testUser = userService.create(testUserDto);

        // when
        List<GrantedAuthority> roles = userService.getRolesByUserId(testUser.getId());

        // then
        assertEquals(roles.size(), 1);
        roles
                .forEach(grantedAuthority -> assertEquals(grantedAuthority.toString(), UserRoleType.USER.name()));
    }

    @Test(expected = UserException.class)
    public void fail_createUserRole() {
        // given
        User testUser = null;

        // when
        userService.createUserRole(testUser, UserRoleType.USER);
    }

    @Test
    public void success_createUserRole() {
        // given
        UserDto testUserDto = TestHelper.getTestUserDto("test1");
        User testUser = userService.create(testUserDto);

        // when
        UserRole userRole = userService.createUserRole(testUser, UserRoleType.USER);

        // then
        assertEquals(userRole.getRole(), UserRoleType.USER);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void fail_create_when_invalid_email() {
        // given
        UserDto testUserDto = TestHelper.getTestUserDto("test1");
        testUserDto.setEmail(null);

        // when
        userService.create(testUserDto);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void fail_create_when_duplicated_email() {
        // given
        UserDto testUserDto1 = TestHelper.getTestUserDto("test1");
        UserDto testUserDto2 = TestHelper.getTestUserDto("test1");

        // when
        userService.create(testUserDto1);
        userService.create(testUserDto2);
    }

    @Test
    public void success_create() {
        // given
        UserDto testUserDto1 = TestHelper.getTestUserDto("test1");
        UserDto testUserDto2 = TestHelper.getTestUserDto("test2");

        // when
        User testUser1 = userService.create(testUserDto1);
        User testUser2 = userService.create(testUserDto2);

        // then
        assertUserDtoAndUser(testUserDto1, testUser1);
        assertUserDtoAndUser(testUserDto2, testUser2);
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
        UserDto testUserDto1 = TestHelper.getTestUserDto("test1");
        UserDto testUserDto2 = TestHelper.getTestUserDto("test2");
        userService.create(testUserDto1);
        userService.create(testUserDto2);

        // when
        Long userCount = userService.count();

        // then
        assertEquals(Math.toIntExact(userCount), 2);
    }

    @Test
    public void success_findAll() {
        // given
        UserDto testUserDto1 = TestHelper.getTestUserDto("test1");
        UserDto testUserDto2 = TestHelper.getTestUserDto("test2");
        userService.create(testUserDto1);
        userService.create(testUserDto2);

        // when
        List<User> testUserList = userService.findAll();

        // then
        assertEquals(testUserList.size(), 2);
    }

    @Test
    public void success_deleteAll() {
        // given
        UserDto testUserDto1 = TestHelper.getTestUserDto("test1");
        UserDto testUserDto2 = TestHelper.getTestUserDto("test2");
        userService.create(testUserDto1);
        userService.create(testUserDto2);

        // when
        userService.deleteAll();

        // then
        List<User> testUserList = userService.findAll();
        assertEquals(testUserList.size(), 0);
    }

    public void assertUserDtoAndUser(UserDto userDto, User user) {
        assertEquals(userDto.getName(), user.getName());
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getProfileImg(), user.getProfileImg());
        assertEquals(user.getEnabled(), true);
        assertThat(userService.matchPassword(userDto.getPassword(), user.getPassword()), is(true));
        assertNotNull(user.getCreatedAt());
        assertNotNull(user.getUpdatedAt());
    }
}
