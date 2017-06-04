package com.board.gd.domain.payment;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by godong9 on 2017. 6. 4..
 */
public interface PaymentInfoRepository extends JpaRepository<PaymentInfo, Long> {
    PaymentInfo findByUserId(Long userId);
}
