package com.board.gd.domain.payment;

import com.board.gd.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by godong9 on 2017. 6. 4..
 */

@Slf4j
@Transactional(readOnly = true)
@Service
public class PaymentService {
    @Autowired
    private PaymentInfoRepository paymentInfoRepository;

    @Transactional(readOnly = false)
    public PaymentInfo createPaymentInfo(PaymentInfoDto paymentInfoDto) {
        User user = User.builder().id(paymentInfoDto.getUserId()).build();
        return paymentInfoRepository.save(PaymentInfo.builder()
                .cardName(paymentInfoDto.getCardName())
                .customerUid(paymentInfoDto.getCustomerUid())
                .user(user)
                .enabled(true)
                .build());
    }

    @Transactional(readOnly = false)
    public void disablePaymentInfo(Long userId) {
        PaymentInfo paymentInfo = paymentInfoRepository.findByUserId(userId);
        paymentInfo.setEnabled(false);
        paymentInfoRepository.save(paymentInfo);
    }
}
