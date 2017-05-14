package com.board.gd.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by godong9 on 2017. 5. 14..
 */

@Repository
public interface PostReportRepository extends JpaRepository<PostReport, Long> {
    PostReport findByPostIdAndUserId(Long postId, Long userId);
}
