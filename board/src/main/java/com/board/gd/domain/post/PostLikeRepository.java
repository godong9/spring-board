package com.board.gd.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by godong9 on 2017. 4. 24..
 */

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    PostLike findByPostIdAndUserId(Long postId, Long userId);
}
