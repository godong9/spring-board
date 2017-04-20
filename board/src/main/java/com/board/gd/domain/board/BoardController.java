package com.board.gd.domain.board;

import com.board.gd.response.ServerResponse;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gd.godong9 on 2017. 4. 20.
 */

@Slf4j
@RestController
public class BoardController {
    @Autowired
    private BoardService boardService;

    /**
     * @api {get} /boards Request Board list
     * @apiName GetBoards
     * @apiGroup Board
     *
     * @apiParam {Number} [size=100] 가져올 개수
     * @apiParam {Number} [page=0] 가져올 페이지
     * @apiParam {String="createdAt,desc", "updatedAt,desc"} [sort=createdAt,desc] 정렬 조건
     *
     * @apiSuccess {Number} status 상태코드
     * @apiSuccess {Number} count 게시판 개수
     * @apiSuccess {Object[]} data 게시판 리스트
     * @apiSuccess {Number} data.id 게시판 id
     * @apiSuccess {String} data.title 게시판 제목
     * @apiSuccess {String} data.code 게시판 코드
     *
     * @apiUse BadRequestError
     */
    @GetMapping("/boards")
    public ServerResponse getBoards(
            @QuerydslPredicate(root = Board.class) Predicate predicate,
            @PageableDefault(size = 100, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Board> postPage = boardService.findAll(predicate, pageable);
        Long count = boardService.count(predicate);
        return ServerResponse.success(BoardResult.getBoardResultList(postPage), count);
    }
}
