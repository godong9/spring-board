package com.board.gd.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by godong9 on 2017. 4. 24..
 */

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    List<PostLike> findByPostIdAndUserId(Long postId, Long userId);
    PostLike findByPostIdAndUserIdAndUnlike(Long postId, Long userId, Boolean unlike);
}
