package com.board.gd.domain.post;

import com.board.gd.domain.post.form.*;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by gd.godong9 on 2017. 4. 10.
 */

@Slf4j
@RestController
public class PostController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    /**
     * @api {get} /posts Request Post list
     * @apiName GetPosts
     * @apiGroup Post
     *
     * @apiParam {Number} [size=20] 가져올 개수
     * @apiParam {Number} [page=0] 가져올 페이지
     * @apiParam {String="created_at,desc", "updated_at,desc"} [sort=created_at,desc] 정렬 조건
     * @apiParam {Number} [user.id] 가져올 유저 id
     * @apiParam {Number} [board.id] 가져올 게시판 id
     *
     * @apiSuccess {Number} status 상태코드
     * @apiSuccess {Number} count 포스트 개수
     * @apiSuccess {Object[]} data 포스트 리스트
     * @apiSuccess {Number} data.id 포스트 id
     * @apiSuccess {String} data.title 포스트 제목
     * @apiSuccess {String} data.content 포스트 내용
     * @apiSuccess {Number} data.view_count 포스트 조회수
     * @apiSuccess {Number} data.comment_count 포스트 댓글수
     * @apiSuccess {Number} data.post_like_count 포스트 좋아요수
     * @apiSuccess {Boolean} data.blocked 포스트 숨김 처리 여부 (true: 신고된 글. 숨김 처리)
     * @apiSuccess {Date} data.created_at 포스트 생성일
     * @apiSuccess {Date} data.updated_at 포스트 수정일
     * @apiSuccess {Object} data.user 포스트 유저
     * @apiSuccess {Number} data.user.id 포스트 유저 id
     * @apiSuccess {String} data.user.name 포스트 유저 이름
     *
     * @apiSampleRequest http://localhost:9700/posts?page=1&size=10&sort=updatedAt,desc
     *
     * @apiUse BadRequestError
     */
    @GetMapping("/posts")
    public ServerResponse getPosts(
            @QuerydslPredicate(root = Post.class) Predicate predicate,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        log.debug("[getPosts] pageable: {}", pageable.toString());
        log.debug("[getPosts] predicate: {}", predicate);
        Page<Post> postPage = postService.findAll(predicate, pageable);
        Long count = postService.count(predicate);
        return ServerResponse.success(PostResult.getPostResultList(postPage), count);
    }

    /**
     * @api {get} /posts/:id Request Post
     * @apiName GetPost
     * @apiGroup Post
     *
     * @apiSuccess {Number} status 상태코드
     * @apiSuccess {Object} data 포스트 객체
     * @apiSuccess {Number} data.id 포스트 id
     * @apiSuccess {String} data.title 포스트 제목
     * @apiSuccess {String} data.content 포스트 내용
     * @apiSuccess {Number} data.view_count 포스트 조회수
     * @apiSuccess {Number} data.comment_count 포스트 댓글수
     * @apiSuccess {Number} data.post_like_count 포스트 좋아요수
     * @apiSuccess {Date} data.created_at 포스트 생성일
     * @apiSuccess {Date} data.updated_at 포스트 수정일
     * @apiSuccess {Object} data.user 포스트 유저
     * @apiSuccess {Number} data.user.id 포스트 유저 id
     * @apiSuccess {String} data.user.name 포스트 유저 이름
     *
     * @apiUse BadRequestError
     */
    @GetMapping("/posts/{id}")
    public ServerResponse getPost(@PathVariable @Valid Long id) {
        Post post = postService.increaseViewCountAndFindOne(id);
        return ServerResponse.success(PostResult.getPostResult(post));
    }

    /**
     * @api {post} /posts Request Post create
     * @apiName CreatePost
     * @apiGroup Post
     *
     * @apiParam {String} title 제목
     * @apiParam {String} content 내용
     * @apiParam {Number} [boardId] 글 작성할 게시판 ID
     *
     * @apiSuccess {Number} status 상태코드
     * @apiSuccess {Object} data 포스트 객체
     * @apiSuccess {Number} data.id 포스트 id
     * @apiSuccess {String} data.title 포스트 제목
     * @apiSuccess {String} data.content 포스트 내용
     * @apiSuccess {Number} data.view_count 포스트 조회수
     * @apiSuccess {Number} data.comment_count 포스트 댓글수
     * @apiSuccess {Number} data.post_like_count 포스트 좋아요수
     * @apiSuccess {Date} data.created_at 포스트 생성일
     * @apiSuccess {Date} data.updated_at 포스트 수정일
     * @apiSuccess {Object} data.user 포스트 유저
     * @apiSuccess {Number} data.user.id 포스트 유저 id
     * @apiSuccess {String} data.user.name 포스트 유저 이름
     *
     * @apiUse BadRequestError
     */
    @PostMapping("/posts")
    public ServerResponse postPost(@RequestBody @Valid CreateForm createForm) {
        createForm.setUserId(userService.getCurrentUser().getId());
        Post post = postService.create(modelMapper.map(createForm, PostDto.class));
        return ServerResponse.success(PostResult.getPostResult(post));
    }

    /**
     * @api {post} /posts/:id/like Request Post like
     * @apiName LikePost
     * @apiGroup Post
     *
     * @apiParam {Boolean} unlike 좋아요: false, 싫어요: true
     *
     * @apiSuccess {Number} status 상태코드
     *
     * @apiUse BadRequestError
     */
    @PostMapping("/posts/{id}/like")
    public ServerResponse postPostLike(@PathVariable @Valid Long id, @RequestBody @Valid PostLikeForm postLikeForm) {
        postLikeForm.setPostId(id);
        postLikeForm.setUserId(userService.getCurrentUser().getId());
        postService.createPostLike(modelMapper.map(postLikeForm, PostLikeDto.class));
        return ServerResponse.success();
    }

    /**
     * @api {post} /posts/:id/report Request Post report
     * @apiName ReportPost
     * @apiGroup Post
     *
     * @apiParam {String} content 신고 내용
     *
     * @apiSuccess {Number} status 상태코드
     *
     * @apiUse BadRequestError
     */
    @PostMapping("/posts/{id}/report")
    public ServerResponse postPostReport(@PathVariable @Valid Long id, @RequestBody @Valid PostReportForm postReportForm) {
        postReportForm.setPostId(id);
        postReportForm.setUserId(userService.getCurrentUser().getId());
        postService.createPostReport(modelMapper.map(postReportForm, PostReportDto.class));
        return ServerResponse.success();
    }

    /**
     * @api {put} /posts/:id Request Post update
     * @apiName UpdatePost
     * @apiGroup Post
     *
     * @apiParam {String} [title] 제목
     * @apiParam {String} [content] 내용
     * @apiParam {Number} [boardId] 글 작성할 게시판 ID
     *
     * @apiSuccess {Number} status 상태코드
     * @apiSuccess {Object} data 포스트 객체
     * @apiSuccess {Number} data.id 포스트 id
     * @apiSuccess {String} data.title 포스트 제목
     * @apiSuccess {String} data.content 포스트 내용
     * @apiSuccess {Number} data.view_count 포스트 조회수
     * @apiSuccess {Number} data.comment_count 포스트 댓글수
     * @apiSuccess {Number} data.post_like_count 포스트 좋아요수
     * @apiSuccess {Date} data.created_at 포스트 생성일
     * @apiSuccess {Date} data.updated_at 포스트 수정일
     * @apiSuccess {Object} data.user 포스트 유저
     * @apiSuccess {Number} data.user.id 포스트 유저 id
     * @apiSuccess {String} data.user.name 포스트 유저 이름
     *
     * @apiUse BadRequestError
     */
    @PutMapping("/posts/{id}")
    public ServerResponse putPost(@PathVariable @Valid Long id, @RequestBody @Valid UpdateForm updateForm) {
        updateForm.setId(id);
        updateForm.setUserId(userService.getCurrentUser().getId());
        Post post = postService.update(modelMapper.map(updateForm, PostDto.class));
        return ServerResponse.success(PostResult.getPostResult(post));
    }

    /**
     * @api {delete} /posts/:id Request Post delete
     * @apiName DeletePost
     * @apiGroup Post
     *
     * @apiSuccess {Number} status 상태코드
     *
     * @apiUse BadRequestError
     */
    @DeleteMapping("/posts/{id}")
    public ServerResponse deletePost(@PathVariable @Valid Long id) {
        DeleteForm deleteForm = new DeleteForm();
        deleteForm.setId(id);
        deleteForm.setUserId(userService.getCurrentUser().getId());
        postService.delete(modelMapper.map(deleteForm, PostDto.class));
        return ServerResponse.success();
    }
}
