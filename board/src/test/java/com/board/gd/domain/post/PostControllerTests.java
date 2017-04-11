package com.board.gd.domain.post;

import com.board.gd.domain.user.User;
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

import javax.servlet.Filter;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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
    private PostTestHelper postTestHelper;

    @Autowired
    private PostService postService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilters(springSecurityFilterChain)
                .build();
    }

    @Test
    public void success_getPosts_when_params_userId() throws Exception {
        // given
        User testUser1 = postTestHelper.saveAndGetTestUser("test1");
        User testUser2 = postTestHelper.saveAndGetTestUser("test2");
        PostDto testPostDto1 = postTestHelper.getTestPostDto(testUser1.getId());
        PostDto testPostDto2 = postTestHelper.getTestPostDto(testUser2.getId());
        postService.save(testPostDto1);
        postService.save(testPostDto2);
        String testUser1Id =  testUser1.getId().toString();
        String testUser2Id =  testUser2.getId().toString();

        // when
        mockMvc.perform(get("/posts")
                .param("userId", testUser1Id)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
//                .andDo(print())
                .andExpect(jsonPath("$.posts", hasSize(1)))
                .andExpect(jsonPath("$.posts[0].id").isNotEmpty())
                .andExpect(jsonPath("$.posts[0].title").value(testPostDto1.getTitle()))
                .andExpect(jsonPath("$.posts[0].content").value(testPostDto1.getContent()))
                .andExpect(jsonPath("$.posts[0].user.id").value(testUser1Id));

        mockMvc.perform(get("/posts")
                .param("userId", testUser2Id)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
//                .andDo(print())
                .andExpect(jsonPath("$.posts", hasSize(1)))
                .andExpect(jsonPath("$.posts[0].id").isNotEmpty())
                .andExpect(jsonPath("$.posts[0].title").value(testPostDto2.getTitle()))
                .andExpect(jsonPath("$.posts[0].content").value(testPostDto2.getContent()))
                .andExpect(jsonPath("$.posts[0].user.id").value(testUser2Id));
    }

}
