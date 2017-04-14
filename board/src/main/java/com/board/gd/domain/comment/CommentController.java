package com.board.gd.domain.comment;

import com.board.gd.domain.comment.form.CreateForm;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
     * @apiSuccess {Object[]} data 댓글 리스트
     * @apiSuccess {Number} data.id 댓글 id
     * @apiSuccess {String} data.content 댓글 내용
     * @apiSuccess {Date} data.createdAt 댓글 생성일
     * @apiSuccess {Date} data.updatedAt 댓글 수정일
     * @apiSuccess {Object} data.user 댓글 유저
     * @apiSuccess {Number} data.user.id 댓글 유저 id
     * @apiSuccess {String} data.user.name 댓글 유저 이름
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

    /**
     * @api {post} /comments Request Comment create
     * @apiName CreateComment
     * @apiGroup Comment
     *
     * @apiParam {Number} postId 제목
     * @apiParam {String} content 내용
     *
     * @apiSuccess {Number} status 상태코드
     * @apiSuccess {String} [message] 메시지
     * @apiSuccess {Object} data 댓글 객체
     * @apiSuccess {Number} data.id 댓글 id
     * @apiSuccess {String} data.content 댓글 내용
     * @apiSuccess {Date} data.createdAt 댓글 생성일
     * @apiSuccess {Date} data.updatedAt 댓글 수정일
     * @apiSuccess {Object} data.user 댓글 유저
     * @apiSuccess {Number} data.user.id 댓글 유저 id
     * @apiSuccess {String} data.user.name 댓글 유저 이름
     */
    @PostMapping("/comments")
    public ServerResponse postComment(@RequestBody @Valid CreateForm createForm) {
        createForm.setUserId(userService.getCurrentUser().getId());
        Comment comment = commentService.create(modelMapper.map(createForm, CommentDto.class));
        return ServerResponse.success(CommentResult.getCommentResult(comment));
    }
}
