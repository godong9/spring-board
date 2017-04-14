package com.board.gd.domain.comment;

import com.board.gd.domain.user.UserService;
import com.board.gd.response.ServerResponse;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gd.godong9 on 2017. 4. 14.
 */

@Slf4j
@RestController
public class CommentController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    /**
     * @api {get} /comments Request Comment list
     * @apiName GetComments
     * @apiGroup Comment
     *
     * @apiParam {Number} [size=20] 가져올 개수
     * @apiParam {Number} [page=0] 가져올 페이지
     * @apiParam {String="createdAt,desc", "updatedAt,desc"} [sort=createdAt,desc] 정렬 조건
     * @apiParam {Number} [post.id] 가져올 포스트 id
     * @apiParam {Number} [user.id] 가져올 유저 id
     *
     * @apiSampleRequest http://localhost:8080/comments?page=1&size=10&sort=updatedAt,desc
     */
    @GetMapping("/comments")
    public ServerResponse getComments(
            @QuerydslPredicate(root = Comment.class) Predicate predicate,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        log.debug("[getPosts] pageable: {}", pageable.toString());
        log.debug("[getPosts] predicate: {}", predicate);
        Page<Comment> commentPage = commentService.findAll(predicate, pageable);
        return ServerResponse.success(CommentResult.getCommentResultList(commentPage));
    }
}
