package com.board.gd.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by gd.godong9 on 2017. 4. 7.
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findByUserId(Long userId);
}
