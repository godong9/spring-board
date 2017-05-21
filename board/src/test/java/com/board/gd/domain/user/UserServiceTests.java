package com.board.gd.domain.user;

import com.board.gd.TestHelper;
import com.board.gd.exception.UserException;
import com.board.gd.mail.MailService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by gd.godong9 on 2017. 4. 3.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTests {
    private static final Date DEFAULT_ROLE_EXPIRED_DATE = new Date(4150537200000L);

    @Autowired
    private UserService userService;

    @MockBean
    private MailService mailService;

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
        assertEquals(null, user);
    }

    @Test
    public void success_findOne() {
        // given
        UserDto testUserDto = TestHelper.getTestUserDto("test1");
        User testUser = userService.create(testUserDto);

        // when
        User user = userService.findOne(testUser.getId());

        // then
        TestHelper.assertUserDtoAndUser(testUserDto, user);
        assertThat(userService.matchPassword(testUserDto.getPassword(), user.getPassword()), is(true));
    }

    @Test
    public void fail_findByEmail() {
        // given
        UserDto testUserDto = TestHelper.getTestUserDto("test1");
        userService.create(testUserDto);

        // when
        User user = userService.findByEmail("test1@");

        // then
        assertEquals(null, user);
    }

    @Test
    public void success_findByEmail() {
        // given
        UserDto testUserDto = TestHelper.getTestUserDto("test1");
        User testUser = userService.create(testUserDto);

        // when
        User user = userService.findByEmail(testUser.getEmail());

        // then
        TestHelper.assertUserDtoAndUser(testUserDto, user);
        assertThat(userService.matchPassword(testUserDto.getPassword(), user.getPassword()), is(true));
    }

    @Test
    public void fail_findRolesByUserId() {
        // given

        // when
        List<GrantedAuthority> roles = userService.findRolesByUserId(-1L);

        // then
        assertEquals(0, roles.size());
    }

    @Test
    public void success_findRolesByUserId() {
        // given
        UserDto testUserDto = TestHelper.getTestUserDto("test1");
        User testUser = userService.create(testUserDto);

        // when
        List<GrantedAuthority> roles = userService.findRolesByUserId(testUser.getId());

        // then
        assertEquals(1, roles.size());
        roles
                .forEach(grantedAuthority -> assertEquals(grantedAuthority.toString(), UserRoleType.USER.name()));
    }

    @Test
    public void success_findRolesByUserId_when_expired_role_exist() {
        // given
        UserDto testUserDto = TestHelper.getTestUserDto("test1");
        User testUser = userService.create(testUserDto);
        Date expiredDate = new Date(new Date().getTime() - (1000 * 60 * 60));
        userService.createUserRole(testUser, UserRoleType.ADMIN, expiredDate);
        // when
        List<GrantedAuthority> roles = userService.findRolesByUserId(testUser.getId());

        // then
        assertEquals(1, roles.size());
        roles
                .forEach(grantedAuthority -> assertEquals(grantedAuthority.toString(), UserRoleType.USER.name()));
    }

    @Test
    public void success_findRolesByUserId_when_two_role_exist() {
        // given
        UserDto testUserDto = TestHelper.getTestUserDto("test1");
        User testUser = userService.create(testUserDto);
        Date notExpiredDate = new Date(new Date().getTime() + (1000 * 60 * 60));
        userService.createUserRole(testUser, UserRoleType.ADMIN, notExpiredDate);
        // when
        List<GrantedAuthority> roles = userService.findRolesByUserId(testUser.getId());

        // then
        assertEquals(2, roles.size());
    }

    @Test(expected = UserException.class)
    public void fail_createUserRole() {
        // given
        User testUser = null;

        // when
        userService.createUserRole(testUser, UserRoleType.USER, DEFAULT_ROLE_EXPIRED_DATE);
    }

    @Test
    public void success_createUserRole() {
        // given
        UserDto testUserDto = TestHelper.getTestUserDto("test1");
        User testUser = userService.create(testUserDto);
        // when
        UserRole userRole = userService.createUserRole(testUser, UserRoleType.USER, DEFAULT_ROLE_EXPIRED_DATE);

        // then
        assertEquals(UserRoleType.USER, userRole.getRole());
    }

    @Test
    public void success_createUserEmail() {
        // given
        UserDto testUserDto = new UserDto();
        testUserDto.setEmail("test@test.com");
        // when
        User testUser = userService.createUserEmail(testUserDto);

        // then
        assertEquals(testUser.getEmail(), "test@test.com");
        assertEquals(testUser.getName(), "");
        assertEquals(testUser.getPassword(), null);
    }

    @Test
    public void success_updateAuthInfo() {
        // given
        UserDto userDto = TestHelper.getTestUserDto("test1");
        User beforeTestUser = userService.create(userDto);
        UserDto testUserDto = new UserDto();
        testUserDto.setEmail(beforeTestUser.getEmail());
        String beforeEmail = beforeTestUser.getEmail();
        String beforeUUID = beforeTestUser.getAuthUUID();

        // when
        User afterTestUser = userService.updateAuthInfo(testUserDto);

        // then
        assertEquals(afterTestUser.getEmail(), beforeEmail);
        assertNotEquals(afterTestUser.getAuthUUID(), beforeUUID);
    }


    @Test
    public void success_createUserEmail_when_already_exist_user() {
        // given
        UserDto testUserDto = new UserDto();
        testUserDto.setEmail("test@test.com");
        User beforeTestUser = userService.createUserEmail(testUserDto);

        // when
        User afterTestUser = userService.createUserEmail(testUserDto);

        // then
        assertEquals(afterTestUser.getId(), beforeTestUser.getId());
    }

    @Test(expected = UserException.class)
    public void fail_createUserEmail_when_already_auth() {
        // given
        UserDto testUserDto = new UserDto();
        testUserDto.setEmail("test@test.com");
        User testUser = userService.createUserEmail(testUserDto);
        userService.authUser(testUser.getId(), testUser.getAuthUUID());

        // when
        userService.createUserEmail(testUserDto);
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
        TestHelper.assertUserDtoAndUser(testUserDto1, testUser1);
        assertThat(userService.matchPassword(testUserDto1.getPassword(), testUser1.getPassword()), is(true));
        TestHelper.assertUserDtoAndUser(testUserDto2, testUser2);
        assertThat(userService.matchPassword(testUserDto2.getPassword(), testUser2.getPassword()), is(true));
    }

    @Test
    public void success_update() {
        // given
        UserDto testUserDto = TestHelper.getTestUserDto("test");
        User testUser = userService.create(testUserDto);

        String changedName = "changedName";
        String changedPassword = "changedPassword";
        UserDto changedUserDto = new UserDto();
        changedUserDto.setId(testUser.getId());
        changedUserDto.setName(changedName);
        changedUserDto.setPassword(changedPassword);

        // when
        User changedUser = userService.update(changedUserDto);

        // then
        assertEquals(testUser.getId(), changedUser.getId());
        assertEquals(changedName, changedUser.getName());
        assertEquals(testUserDto.getEmail(), changedUser.getEmail());
        assertNotEquals(testUserDto.getPassword(), changedUser.getPassword());
    }

    @Test
    public void success_count_0() {
        // given

        // when
        Long userCount = userService.count();

        // then
        assertEquals(0, Math.toIntExact(userCount));
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
        assertEquals(2, Math.toIntExact(userCount));
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
        assertEquals(2, testUserList.size());
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
        assertEquals(0, testUserList.size());
    }

    @Test(expected = UserException.class)
    public void fail_authUser_not_exist_user() {
        // given
        UserDto testUserDto1 = TestHelper.getTestUserDto("test1");
        User testUser1 = userService.create(testUserDto1);

        // when
        userService.authUser(-1L, testUser1.getAuthUUID());
    }

    @Test(expected = UserException.class)
    public void fail_authUser_invalid_uuid() {
        // given
        UserDto testUserDto1 = TestHelper.getTestUserDto("test1");
        User testUser1 = userService.create(testUserDto1);

        // when
        userService.authUser(testUser1.getId(), "failuuid");
    }

    @Test
    public void success_authUser() {
        // given
        UserDto testUserDto1 = TestHelper.getTestUserDto("test1");
        User testUser1 = userService.create(testUserDto1);

        // when
        userService.authUser(testUser1.getId(), testUser1.getAuthUUID());

        // then
        User afterTestUser1 = userService.findOne(testUser1.getId());
        assertThat(afterTestUser1.getEnabled(), is(true));
    }

    @Test
    public void success_sendAuthEmail() {
        // given
        User testUser = User.builder()
                .id(1L)
                .email("test@test.com")
                .authUUID(UUID.randomUUID().toString())
                .enabled(false)
                .build();

        // when
        userService.sendAuthEmail(testUser, "auth");
    }
}
