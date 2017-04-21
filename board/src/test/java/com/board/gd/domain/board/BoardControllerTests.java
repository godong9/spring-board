package com.board.gd.domain.board;

import com.board.gd.TestHelper;
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

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by gd.godong9 on 2017. 4. 21.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BoardControllerTests {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private BoardService boardService;

    @Before
    public void setup() {
        boardService.deleteAll();

        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void success_getBoards() throws Exception {
        // given
        BoardDto testBoardDto1 = TestHelper.getTestBoardDto("code1");
        BoardDto testBoardDto2 = TestHelper.getTestBoardDto("code2");
        BoardDto testBoardDto3 = TestHelper.getTestBoardDto("code3");
        boardService.create(testBoardDto1);
        boardService.create(testBoardDto2);
        boardService.create(testBoardDto3);

        // when
        mockMvc.perform(get("/boards")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(3))
                .andExpect(jsonPath("$.data", hasSize(3)));
    }
}
