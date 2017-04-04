package com.board.gd.user;

import com.board.gd.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by gd.godong9 on 2017. 4. 4.
 */

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTests {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void success_signup() throws Exception {
        // given
        String userName = "test";
        String userPassword = "test";
        String userEmail = "test@test.com";
        UserForm form = new UserForm();
        form.setName(userName);
        form.setEmail(userEmail);
        form.setPassword(userPassword);

        given(userService.save(any(UserDto.class))).willReturn(User.builder()
                .id(1)
                .name(userName)
                .password(userPassword)
                .email(userEmail)
                .build());

        // when
        mockMvc.perform(post("/users/signup")
                .content(JsonUtils.toJson(form))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.name").value(userName))
                .andExpect(jsonPath("$.user.email").value(userEmail));
    }

}
