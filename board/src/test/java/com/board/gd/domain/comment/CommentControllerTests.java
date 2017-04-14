package com.board.gd.domain.comment;

import com.board.gd.TestHelper;
import com.board.gd.domain.post.Post;
import com.board.gd.domain.post.PostDto;
import com.board.gd.domain.post.PostService;
import com.board.gd.domain.comment.form.CreateForm;
import com.board.gd.domain.user.User;
import com.board.gd.domain.user.UserService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by gd.godong9 on 2017. 4. 13.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CommentControllerTests {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @MockBean
    private UserService userService;

    @Before
    public void setup() {
        commentService.deleteAll();

        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilters(springSecurityFilterChain)
                .build();
    }

    @Test
    public void success_getComments() throws Exception {
        // given
        given(userService.findOne(1L)).willReturn(TestHelper.getTestUser(1L));
        given(userService.findOne(2L)).willReturn(TestHelper.getTestUser(2L));

        User testUser1 = TestHelper.getTestUser(1L);
        User testUser2 = TestHelper.getTestUser(2L);
        PostDto testPostDto1 = TestHelper.getTestPostDto(testUser1.getId());
        PostDto testPostDto2 = TestHelper.getTestPostDto(testUser2.getId());

        Post testPost1 = postService.create(testPostDto1);
        Post testPost2 = postService.create(testPostDto2);
        String testPost1Id = testPost1.getId().toString();
        String testPost2Id = testPost2.getId().toString();
        String testUser1Id = testUser1.getId().toString();
        String testUser2Id = testUser2.getId().toString();

        CommentDto testCommentDto1 = TestHelper.getTestCommentDto(testUser1.getId(), testPost1.getId());
        CommentDto testCommentDto2 = TestHelper.getTestCommentDto(testUser2.getId(), testPost2.getId());
        commentService.create(testCommentDto1);
        commentService.create(testCommentDto2);

        // when
        mockMvc.perform(get("/comments")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.data", hasSize(2)));

        mockMvc.perform(get("/comments")
                .param("post.id", testPost1Id)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].id").isNotEmpty())
                .andExpect(jsonPath("$.data[0].content").value(testCommentDto1.getContent()))
                .andExpect(jsonPath("$.data[0].user.id").value(testUser1Id));

        mockMvc.perform(get("/comments")
                .param("post.id", testPost2Id)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].id").isNotEmpty())
                .andExpect(jsonPath("$.data[0].content").value(testCommentDto2.getContent()))
                .andExpect(jsonPath("$.data[0].user.id").value(testUser2Id));
    }

    @Test
    public void success_postComment() throws Exception {
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

        PostDto testPostDto = TestHelper.getTestPostDto(1L);
        Post testPost = postService.create(testPostDto);

        String content = "test content";
        CreateForm form = new CreateForm();
        form.setContent(content);
        form.setPostId(testPost.getId());

        // when
        mockMvc.perform(post("/comments")
                .content(JsonUtils.toJson(form))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").isNotEmpty())
                .andExpect(jsonPath("$.data.content").value(content))
                .andExpect(jsonPath("$.data.created_at").isNotEmpty())
                .andExpect(jsonPath("$.data.updated_at").isNotEmpty())
                .andExpect(jsonPath("$.data.user.id").value(1L));
    }

}
