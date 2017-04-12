package com.board.gd.domain.post;

import com.board.gd.domain.post.form.CreateForm;
import com.board.gd.domain.post.form.DeleteForm;
import com.board.gd.domain.post.form.UpdateForm;
import com.board.gd.domain.user.UserService;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
     * @apiParam {String="createdAt,desc", "updatedAt,desc"} [sort=createdAt,desc] 정렬 조건
     * @apiParam {Number} [user.id] 가져올 유저 id
     *
     * @apiSampleRequest http://localhost:8080/posts?page=1&size=10&sort=updatedAt,desc
     */
    @GetMapping("/posts")
    public PostResult getPosts(
            @QuerydslPredicate(root = Post.class) Predicate predicate,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        log.debug("[getPosts] pageable: {}", pageable.toString());
        log.debug("[getPosts] predicate: {}", predicate);
        return PostResult.from(postService.findAll(predicate, pageable), null);
    }

    /**
     * @api {get} /posts/:id Request Post
     * @apiName GetPost
     * @apiGroup Post
     *
     * @apiSuccess {Number} status 상태코드
     * @apiSuccess {String} [msg] 메시지
     * @apiSuccess {Object} post 포스트 객체
     * @apiSuccess {String} post.id 포스트 id
     * @apiSuccess {String} post.title 포스트 제목
     * @apiSuccess {String} post.content 포스트 내용
     * @apiSuccess {Date} post.createdAt 포스트 생성일
     * @apiSuccess {Date} post.updatedAt 포스트 수정일
     * @apiSuccess {Object} post.user 포스트 유저
     * @apiSuccess {String} post.user.id 포스트 유저 id
     *
     * @apiSampleRequest http://localhost:8080/posts?page=1&size=10&sort=updatedAt,desc
     */
    @GetMapping("/posts/{id}")
    public PostResult getPost(@PathVariable @Valid Long id) {
        return PostResult.from(postService.findOne(id), null);
    }

    /**
     * @api {post} /posts Request Post create
     * @apiName CreatePost
     * @apiGroup Post
     *
     * @apiParam {String} title 제목
     * @apiParam {String} content 내용
     *
     * @apiSuccess {Number} status 상태코드
     * @apiSuccess {String} [msg] 메시지
     * @apiSuccess {Object} post 포스트 객체
     * @apiSuccess {String} post.id 포스트 id
     * @apiSuccess {String} post.title 포스트 제목
     * @apiSuccess {String} post.content 포스트 내용
     * @apiSuccess {Date} post.createdAt 포스트 생성일
     * @apiSuccess {Date} post.updatedAt 포스트 수정일
     * @apiSuccess {Object} post.user 포스트 유저
     * @apiSuccess {String} post.user.id 포스트 유저 id
     */
    @PostMapping("/posts")
    public PostResult postPost(@RequestBody @Valid CreateForm createForm) {
        createForm.setUserId(userService.getCurrentUser().getId());
        Post post = postService.create(modelMapper.map(createForm, PostDto.class));
        return PostResult.from(post, null);
    }

    /**
     * @api {put} /posts/:id Request Post update
     * @apiName UpdatePost
     * @apiGroup Post
     *
     * @apiParam {String} [title] 제목
     * @apiParam {String} [content] 내용
     *
     * @apiSuccess {Number} status 상태코드
     * @apiSuccess {String} [msg] 메시지
     * @apiSuccess {Object} post 포스트 객체
     * @apiSuccess {String} post.id 포스트 id
     * @apiSuccess {String} post.title 포스트 제목
     * @apiSuccess {String} post.content 포스트 내용
     * @apiSuccess {Date} post.createdAt 포스트 생성일
     * @apiSuccess {Date} post.updatedAt 포스트 수정일
     * @apiSuccess {Object} post.user 포스트 유저
     * @apiSuccess {String} post.user.id 포스트 유저 id
     */
    @PutMapping("/posts/{id}")
    public PostResult putPost(@PathVariable @Valid Long id, @RequestBody @Valid UpdateForm updateForm) {
        updateForm.setId(id);
        updateForm.setUserId(userService.getCurrentUser().getId());
        Post post = postService.update(modelMapper.map(updateForm, PostDto.class));
        return PostResult.from(post, null);
    }

    /**
     * @api {delete} /posts/:id Request Post delete
     * @apiName DeletePost
     * @apiGroup Post
     *
     * @apiSuccess {Number} status 상태코드
     * @apiSuccess {String} [msg] 메시지
     */
    @DeleteMapping("/posts/{id}")
    public PostResult deletePost(@PathVariable @Valid Long id) {
        DeleteForm deleteForm = new DeleteForm();
        deleteForm.setId(id);
        deleteForm.setUserId(userService.getCurrentUser().getId());
        postService.delete(modelMapper.map(deleteForm, PostDto.class));
        return PostResult.from(HttpStatus.OK, "success");
    }
}
