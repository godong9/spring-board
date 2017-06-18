package com.board.gd.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * Created by gd.godong9 on 2017. 4. 7.
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findByUserId(Long userId);
    List<UserRole> findByExpiredAtBefore(@Temporal(TemporalType.TIMESTAMP) Date date);
}
