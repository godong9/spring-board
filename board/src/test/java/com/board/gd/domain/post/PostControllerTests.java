package com.board.gd.domain.post;

import com.board.gd.TestHelper;
import com.board.gd.domain.post.form.CreateForm;
import com.board.gd.domain.post.form.UpdateForm;
import com.board.gd.domain.stock.Stock;
import com.board.gd.domain.stock.StockService;
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

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    private PostService postService;

    @MockBean
    private UserService userService;

    @MockBean
    private StockService stockService;

    @Before
    public void setup() {
        postService.deleteAll();

        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void success_getPosts() throws Exception {
        // given
        given(userService.findOne(1L)).willReturn(TestHelper.getTestUser(1L));
        given(userService.findOne(2L)).willReturn(TestHelper.getTestUser(2L));

        User testUser1 = TestHelper.getTestUser(1L);
        User testUser2 = TestHelper.getTestUser(2L);
        PostDto testPostDto1 = TestHelper.getTestPostDto(testUser1.getId());
        PostDto testPostDto2 = TestHelper.getTestPostDto(testUser2.getId());
        Post post1 = postService.create(testPostDto1);
        Thread.sleep(1000);
        Post post2 = postService.create(testPostDto2);
        String testUser1Id = testUser1.getId().toString();
        String testUser2Id = testUser2.getId().toString();

        // when
        mockMvc.perform(get("/posts")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(2))
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[0].id").value(post2.getId()))
                .andExpect(jsonPath("$.data[1].id").value(post1.getId()));

        mockMvc.perform(get("/posts")
                .param("user.id", testUser1Id)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(1))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].id").value(post1.getId()))
                .andExpect(jsonPath("$.data[0].title").value(testPostDto1.getTitle()))
                .andExpect(jsonPath("$.data[0].content").value(testPostDto1.getContent()))
                .andExpect(jsonPath("$.data[0].view_count").value(0))
                .andExpect(jsonPath("$.data[0].comment_count").value(0))
                .andExpect(jsonPath("$.data[0].post_like_count").value(0))
                .andExpect(jsonPath("$.data[0].blocked").value(false))
                .andExpect(jsonPath("$.data[0].user.id").value(testUser1Id));

        mockMvc.perform(get("/posts")
                .param("user.id", testUser2Id)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(1))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].id").isNotEmpty())
                .andExpect(jsonPath("$.data[0].title").value(testPostDto2.getTitle()))
                .andExpect(jsonPath("$.data[0].content").value(testPostDto2.getContent()))
                .andExpect(jsonPath("$.data[0].user.id").value(testUser2Id));
    }

    @Test
    public void success_getPosts_by_stockId() throws Exception {
        // given
        Long stockId = 1L;
        given(userService.findOne(1L)).willReturn(TestHelper.getTestUser(1L));
        given(userService.findOne(2L)).willReturn(TestHelper.getTestUser(2L));
        given(stockService.findOne(1L)).willReturn(TestHelper.getTestStock(1L));

        User testUser1 = TestHelper.getTestUser(1L);
        User testUser2 = TestHelper.getTestUser(2L);
        PostDto testPostDto1 = TestHelper.getTestPostDto(testUser1.getId(), stockId);
        PostDto testPostDto2 = TestHelper.getTestPostDto(testUser2.getId(), stockId);
        postService.create(testPostDto1);
        postService.create(testPostDto2);

        // when
        mockMvc.perform(get("/posts")
                .param("stock.id", "2")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(0))
                .andExpect(jsonPath("$.data", hasSize(0)));

        mockMvc.perform(get("/posts")
                .param("stock.id", stockId.toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(2))
                .andExpect(jsonPath("$.data", hasSize(2)));
    }

    @Test
    public void success_getPosts_orderBy_updatedAt() throws Exception {
        // given
        Long stockId = 1L;
        given(userService.findOne(1L)).willReturn(TestHelper.getTestUser(1L));

        User testUser1 = TestHelper.getTestUser(1L);
        PostDto testPostDto1 = TestHelper.getTestPostDto(testUser1.getId(), stockId);
        PostDto testPostDto2 = TestHelper.getTestPostDto(testUser1.getId(), stockId);
        Post post1 = postService.create(testPostDto1);
        Thread.sleep(1000);
        Post post2 = postService.create(testPostDto2);

        // when
        mockMvc.perform(get("/posts")
                .param("updated_at,desc", stockId.toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(2))
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[0].id").value(post2.getId()))
                .andExpect(jsonPath("$.data[1].id").value(post1.getId()));
    }

    @Test
    public void success_getPost() throws Exception {
        // given
        given(userService.getCurrentUser()).willReturn(User.builder()
                .id(1L)
                .name("test")
                .email("test@test.com")
                .build());
        given(userService.findOne(1L)).willReturn(TestHelper.getTestUser(1L));

        User testUser = TestHelper.getTestUser(1L);
        PostDto testPostDto = TestHelper.getTestPostDto(testUser.getId());
        Post testPost = postService.create(testPostDto);

        // when
        mockMvc.perform(get("/posts/" + testPost.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.data.id").isNotEmpty())
                .andExpect(jsonPath("$.data.title").value(testPostDto.getTitle()))
                .andExpect(jsonPath("$.data.content").value(testPostDto.getContent()))
                .andExpect(jsonPath("$.data.user.id").value(testUser.getId()))
                .andExpect(jsonPath("$.data.stock").doesNotExist())
                .andExpect(jsonPath("$.data.comment_count").value(0))
                .andExpect(jsonPath("$.data.post_like_count").value(0))
                .andExpect(jsonPath("$.data.view_count").value(1));
    }

    @Test
    public void success_getPost_when_stockId_exist() throws Exception {
        // given
        given(userService.getCurrentUser()).willReturn(User.builder()
                .id(1L)
                .name("test")
                .email("test@test.com")
                .build());
        given(userService.findOne(1L)).willReturn(TestHelper.getTestUser(1L));
        given(stockService.findOne(1L)).willReturn(TestHelper.getTestStock(1L));

        User testUser = TestHelper.getTestUser(1L);
        Stock testStock = TestHelper.getTestStock(1L);
        PostDto testPostDto = TestHelper.getTestPostDto(testUser.getId());
        testPostDto.setStockId(1L);
        Post testPost = postService.create(testPostDto);

        // when
        mockMvc.perform(get("/posts/" + testPost.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.data.id").isNotEmpty())
                .andExpect(jsonPath("$.data.title").value(testPostDto.getTitle()))
                .andExpect(jsonPath("$.data.content").value(testPostDto.getContent()))
                .andExpect(jsonPath("$.data.user.id").value(testUser.getId()))
                .andExpect(jsonPath("$.data.stock.id").value(testStock.getId()))
                .andExpect(jsonPath("$.data.comment_count").value(0))
                .andExpect(jsonPath("$.data.post_like_count").value(0))
                .andExpect(jsonPath("$.data.view_count").value(1));
    }

    @Test
    public void fail_postPost_when_not_login() throws Exception {
        // given
        given(userService.getCurrentUser()).willThrow(new UserException("Not authenticated!"));

        String title = "test title";
        String content = "test content";
        CreateForm form = new CreateForm();
        form.setTitle(title);
        form.setContent(content);

        // when
        mockMvc.perform(post("/posts")
                .content(JsonUtils.toJson(form))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.error.message").value("Not authenticated!"));
    }

    @Test
    public void success_postPost() throws Exception {
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
        CreateForm form = new CreateForm();
        form.setTitle(title);
        form.setContent(content);

        // when
        mockMvc.perform(post("/posts")
                .content(JsonUtils.toJson(form))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").isNotEmpty())
                .andExpect(jsonPath("$.data.title").value(title))
                .andExpect(jsonPath("$.data.content").value(content))
                .andExpect(jsonPath("$.data.created_at").isNotEmpty())
                .andExpect(jsonPath("$.data.updated_at").isNotEmpty())
                .andExpect(jsonPath("$.data.user.id").value(1L));
    }

    @Test
    public void success_putPost() throws Exception {
        // given
        given(userService.getCurrentUser()).willReturn(User.builder()
                .id(1L)
                .name("test")
                .email("test@test.com")
                .build());
        given(userService.findOne(1L)).willReturn(TestHelper.getTestUser(1L));

        User testUser = TestHelper.getTestUser(1L);
        PostDto testPostDto = TestHelper.getTestPostDto(testUser.getId());
        Post testPost = postService.create(testPostDto);

        String changedTitle = "changed title";
        String changedContent = "changed content";

        UpdateForm form = new UpdateForm();
        form.setTitle(changedTitle);
        form.setContent(changedContent);

        // when
        mockMvc.perform(put("/posts/" + testPost.getId())
                .content(JsonUtils.toJson(form))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(testPost.getId()))
                .andExpect(jsonPath("$.data.title").value(changedTitle))
                .andExpect(jsonPath("$.data.content").value(changedContent))
                .andExpect(jsonPath("$.data.created_at").isNotEmpty())
                .andExpect(jsonPath("$.data.updated_at").isNotEmpty())
                .andExpect(jsonPath("$.data.user.id").value(1L));
    }

    @Test
    public void fail_deletePost_when_empty_id() throws Exception {
        // when
        mockMvc.perform(delete("/posts/")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void success_deletePost() throws Exception {
        // given
        given(userService.getCurrentUser()).willReturn(User.builder()
                .id(1L)
                .name("test")
                .email("test@test.com")
                .build());
        given(userService.findOne(1L)).willReturn(TestHelper.getTestUser(1L));

        User testUser = TestHelper.getTestUser(1L);
        PostDto testPostDto = TestHelper.getTestPostDto(testUser.getId());
        Post testPost = postService.create(testPostDto);

        // when
        mockMvc.perform(delete("/posts/" + testPost.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
