package com.board.gd.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by gd.godong9 on 2017. 4. 3.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
