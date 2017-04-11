package com.board.gd.domain.post;

import com.board.gd.domain.post.form.PostForm;
import com.board.gd.domain.user.UserService;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @apiName PostList
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

    @PostMapping("/posts")
    public PostResult postPost(@RequestBody @Valid PostForm postForm) {
        postForm.setUserId(userService.getCurrentUser().getId());
        Post post = postService.create(modelMapper.map(postForm, PostDto.class));
        return PostResult.from(post, null);
    }

    @PutMapping("/posts")
    public PostResult putPost(@RequestBody @Valid PostForm postForm) {
        postForm.setUserId(userService.getCurrentUser().getId());
        Post post = postService.create(modelMapper.map(postForm, PostDto.class));
        return PostResult.from(post, null);
    }
}
