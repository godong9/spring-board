package com.board.gd.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by gd.godong9 on 2017. 4. 4.
 */

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
