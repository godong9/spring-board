package com.board.gd.domain.post;

/**
 * Created by gd.godong9 on 2017. 4. 4.
 */

import com.board.gd.exception.PostException;
import com.board.gd.domain.user.User;
import com.board.gd.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    public Post findOne(Long id) {
        return postRepository.findOne(id);
    }

    public Page<Post> findAll(Pageable pageable) {
        if (pageable == null) {
            throw new PostException("Invalid pageable!");
        }
        return postRepository.findAll(pageable);
    }

    @Transactional
    public Post save(PostDto postDto) {
        User user = userService.findOne(postDto.getUserId());
        if (user == null) {
            throw new PostException("Not exist user!");
        }
        return postRepository.save(Post.builder()
                .id(postDto.getId())
                .type(postDto.getType())
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .viewCount(0L)
                .user(user)
                .build());
    }

    public long count() {
        return postRepository.count();
    }

    public void delete(Long id) {
        postRepository.delete(id);
    }

    public void deleteAll() {
        postRepository.deleteAll();
    }
}
