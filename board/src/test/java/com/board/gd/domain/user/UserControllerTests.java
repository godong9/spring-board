package com.board.gd.domain.user;

import com.board.gd.domain.user.form.LoginForm;
import com.board.gd.domain.user.form.SignupForm;
import com.board.gd.utils.JsonUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by gd.godong9 on 2017. 4. 4.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserControllerTests {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;

    @MockBean
    private UserService userService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilters(springSecurityFilterChain)
                .build();
    }

    private static final BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();

    @Test
    public void fail_signup_not_exist_email() throws Exception {
        // given
        String userName = "test111";
        String userPassword = "test111";
        SignupForm form = new SignupForm();
        form.setName(userName);
        form.setPassword(userPassword);

        // when
        mockMvc.perform(post("/users/signup")
                .content(JsonUtils.toJson(form))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void success_signup() throws Exception {
        // given
        String userName = "test111";
        String userPassword = "test111";
        String userEmail = "test111@test.com";
        SignupForm form = new SignupForm();
        form.setName(userName);
        form.setEmail(userEmail);
        form.setPassword(userPassword);

        given(userService.create(any(UserDto.class))).willReturn(User.builder()
                .id(1L)
                .name(userName)
                .email(userEmail)
                .build());

        // when
        mockMvc.perform(post("/users/signup")
                .content(JsonUtils.toJson(form))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").isNotEmpty())
                .andExpect(jsonPath("$.data.name").value(userName));
    }

    @Test
    public void fail_login() throws Exception {
        // given
        String userEmail = "test111@test.com";
        String userPassword = "test111";
        LoginForm form = new LoginForm();
        form.setEmail(userEmail);
        form.setPassword(userPassword);

        // when
        mockMvc.perform(post("/users/login")
                .content(JsonUtils.toJson(form))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void success_login() throws Exception {
        // given
        String userName = "test111";
        String userEmail = "test111@test.com";
        String userPassword = "test";
        LoginForm form = new LoginForm();
        form.setEmail(userEmail);
        form.setPassword(userPassword);

        given(userService.findByEmail(any(String.class))).willReturn(User.builder()
                .id(1L)
                .name(userName)
                .password(bcryptEncoder.encode(userPassword))
                .email(userEmail)
                .build());

        given(userService.findRolesByUserId(any(Long.class))).willReturn(null);
        given(userService.matchPassword(any(String.class), any(String.class))).willReturn(true);

        // when
        mockMvc.perform(post("/users/login")
                .content(JsonUtils.toJson(form))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

}
