package com.board.gd.domain.payment;

import com.board.gd.domain.user.User;
import com.board.gd.domain.user.UserService;
import com.board.gd.iamport.IamportManager;
import com.board.gd.iamport.SubscribeRequestDto;
import com.board.gd.response.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by godong9 on 2017. 5. 14..
 */

@Slf4j
@RestController
public class PaymentController {
    @Autowired
    private UserService userService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    IamportManager iamportManager;

    @PostMapping("/payments/subscribe")
    public ServerResponse postPaymentSubscribe(@RequestBody @Valid SubscribeRequestDto subscribeRequestDto) {
        User user = userService.getCurrentUser();
//        subscribeRequestDto.setCustomer_uid(user.getId().toString());
//        subscribeRequestDto.setCustomer_email(user.getEmail());
        // TODO: 테스트 후 위 코드 주석 풀고 밑 코드 삭제 필요!
        subscribeRequestDto.setCustomer_uid("customer_1234");
        subscribeRequestDto.setCustomer_email("godong9@gmail.com");

        PaymentInfoDto paymentInfoDto = iamportManager.postSubscribeCustomer(subscribeRequestDto);
        paymentInfoDto.setUserId(user.getId());
        paymentService.createPaymentInfo(paymentInfoDto);
        return ServerResponse.success();
    }
}
