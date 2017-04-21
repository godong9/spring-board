package com.board.gd.domain.board;

import com.board.gd.TestHelper;
import com.querydsl.core.types.Predicate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by gd.godong9 on 2017. 4. 21.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BoardServiceTests {
    @Autowired
    private BoardService boardService;

    @Before
    public void setUp() {
        boardService.deleteAll();
    }

    @Test
    public void success_findAll_sortBy_id_desc() {
        // given
        BoardDto testBoardDto1 = TestHelper.getTestBoardDto("code1");
        BoardDto testBoardDto2 = TestHelper.getTestBoardDto("code2");
        BoardDto testBoardDto3 = TestHelper.getTestBoardDto("code3");
        boardService.create(testBoardDto1);
        boardService.create(testBoardDto2);
        boardService.create(testBoardDto3);
        Pageable pageRequest = new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "id"));
        Predicate predicateMock = mock(Predicate.class);

        // when
        Page<Board> result = boardService.findAll(predicateMock, pageRequest);
        List<Board> boards = result.getContent();

        // then
        assertEquals(boards.size(), 3);
        Board board3 = boards.get(0);
        Board board2 = boards.get(1);
        Board board1 = boards.get(2);
        TestHelper.assertBoardDtoAndBoard(testBoardDto3, board3);
        TestHelper.assertBoardDtoAndBoard(testBoardDto2, board2);
        TestHelper.assertBoardDtoAndBoard(testBoardDto1, board1);
    }
}
