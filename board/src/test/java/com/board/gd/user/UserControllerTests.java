package com.board.gd.user;

import com.board.gd.user.form.SignupForm;
import com.board.gd.utils.JsonUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

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

        // when
        mockMvc.perform(post("/users/signup")
                .content(JsonUtils.toJson(form))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.id").isNotEmpty())
                .andExpect(jsonPath("$.user.name").value(userName));
    }

}
