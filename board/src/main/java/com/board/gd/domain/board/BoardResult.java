package com.board.gd.domain.board;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gd.godong9 on 2017. 4. 20.
 */

@Data
public class BoardResult {
    private Long id;
    private String title;
    private String code;

    public static BoardResult getBoardResult(Board board) {
        BoardResult boardResult = new BoardResult();
        boardResult.setId(board.getId());
        boardResult.setTitle(board.getTitle());
        boardResult.setCode(board.getCode());

        return boardResult;
    }

    public static List<BoardResult> getBoardResultList(Page<Board> boardPage) {
        List<Board> boardList = boardPage.getContent();
        return boardList.stream()
                .map(board -> getBoardResult(board))
                .collect(Collectors.toList());
    }
}
