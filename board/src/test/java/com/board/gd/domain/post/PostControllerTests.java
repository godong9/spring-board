package com.board.gd.domain.post;

import com.board.gd.TestHelper;
import com.board.gd.domain.post.form.PostForm;
import com.board.gd.domain.user.User;
import com.board.gd.domain.user.UserService;
import com.board.gd.exception.UserException;
import com.board.gd.utils.JsonUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by gd.godong9 on 2017. 4. 10.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PostControllerTests {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private PostService postService;

    @MockBean
    private UserService userService;

    @Before
    public void setup() {
        postService.deleteAll();

        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilters(springSecurityFilterChain)
                .build();
    }

    @Test
    public void success_getPosts_when_params_userId() throws Exception {
        // given
        given(userService.findOne(1L)).willReturn(TestHelper.getTestUser(1L));
        given(userService.findOne(2L)).willReturn(TestHelper.getTestUser(2L));

        User testUser1 = TestHelper.getTestUser(1L);
        User testUser2 = TestHelper.getTestUser(2L);
        PostDto testPostDto1 = TestHelper.getTestPostDto(testUser1.getId());
        PostDto testPostDto2 = TestHelper.getTestPostDto(testUser2.getId());
        postService.create(testPostDto1);
        postService.create(testPostDto2);
        String testUser1Id = testUser1.getId().toString();
        String testUser2Id = testUser2.getId().toString();

        // when
        mockMvc.perform(get("/posts")
                .param("user.id", testUser1Id)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.posts", hasSize(1)))
                .andExpect(jsonPath("$.posts[0].id").isNotEmpty())
                .andExpect(jsonPath("$.posts[0].title").value(testPostDto1.getTitle()))
                .andExpect(jsonPath("$.posts[0].content").value(testPostDto1.getContent()))
                .andExpect(jsonPath("$.posts[0].user.id").value(testUser1Id));

        mockMvc.perform(get("/posts")
                .param("user.id", testUser2Id)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.posts", hasSize(1)))
                .andExpect(jsonPath("$.posts[0].id").isNotEmpty())
                .andExpect(jsonPath("$.posts[0].title").value(testPostDto2.getTitle()))
                .andExpect(jsonPath("$.posts[0].content").value(testPostDto2.getContent()))
                .andExpect(jsonPath("$.posts[0].user.id").value(testUser2Id));
    }

    @Test
    public void fail_postPost_insert_when_not_login() throws Exception {
        // given
        given(userService.getCurrentUser()).willThrow(new UserException("Not authenticated!"));

        String title = "test title";
        String content = "test content";
        PostForm form = new PostForm();
        form.setTitle(title);
        form.setContent(content);

        // when
        mockMvc.perform(post("/posts")
                .content(JsonUtils.toJson(form))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").value("Not authenticated!"));
    }

    @Test
    public void success_postPost_insert() throws Exception {
        // given
        given(userService.getCurrentUser()).willReturn(User.builder()
                .id(1L)
                .name("test")
                .email("test@test.com")
                .build());

        given(userService.findOne(any(Long.class))).willReturn(User.builder()
                .id(1L)
                .name("test")
                .email("test@test.com")
                .build());

        String title = "test title";
        String content = "test content";
        PostForm form = new PostForm();
        form.setTitle(title);
        form.setContent(content);

        // when
        mockMvc.perform(post("/posts")
                .content(JsonUtils.toJson(form))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.post.id").isNotEmpty())
                .andExpect(jsonPath("$.post.title").value(title))
                .andExpect(jsonPath("$.post.content").value(content))
                .andExpect(jsonPath("$.post.user.id").value(1L));
    }

}
