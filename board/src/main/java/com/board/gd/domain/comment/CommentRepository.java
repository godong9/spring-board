package com.board.gd.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by gd.godong9 on 2017. 4. 12.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
}
