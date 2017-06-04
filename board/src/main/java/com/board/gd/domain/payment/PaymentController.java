package com.board.gd.domain.payment;

import com.board.gd.domain.user.UserService;
import com.board.gd.iamport.CustomerRequestDto;
import com.board.gd.iamport.IamportManager;
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
    IamportManager iamportManager;

    @PostMapping("/payments/subscribe")
    public ServerResponse postPaymentSubscribe(@RequestBody @Valid CustomerRequestDto customerRequestDto) {
//        User user = userService.getCurrentUser();
//        customerRequestDto.setCustomer_uid(user.getId().toString());
//        customerRequestDto.setCustomer_email(user.getEmail());

        customerRequestDto.setCustomer_uid("customer_1234");
        customerRequestDto.setCustomer_email("godong9@gmail.com");

        iamportManager.postSubscribeCustomer(customerRequestDto);
        return ServerResponse.success();
    }

}
