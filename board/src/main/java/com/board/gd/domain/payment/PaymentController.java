package com.board.gd.domain.payment;

import com.board.gd.iamport.CustomerDto;
import com.board.gd.iamport.IamportManager;
import com.board.gd.response.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by godong9 on 2017. 5. 14..
 */

@Slf4j
@RestController
public class PaymentController {
    @Autowired
    IamportManager iamportManager;

    @PostMapping("/payment/subscribe")
    public ServerResponse postPaymentSubscribe(@RequestBody @Valid CustomerDto customerDto) {
        iamportManager.postSubscribeCustomer(customerDto);
        return ServerResponse.success();
    }

    @GetMapping("/payment/success")
    public ServerResponse getPaymentSuccess(HttpServletRequest param) {
        param.getParameterMap().forEach((s, strings) -> {
            log.info("Key: {}", s);
            log.info("Value: {}", strings[0]);
        });
        return ServerResponse.success();
    }

    @GetMapping("/payment/cancel")
    public ServerResponse getPaymentCancel(HttpServletRequest param) {
        param.getParameterMap().forEach((s, strings) -> {
            log.info("Key: {}", s);
            log.info("Value: {}", strings[0]);
        });
        return ServerResponse.success();
    }

    @GetMapping("/payment/callback")
    public ServerResponse getPaymentCallback(HttpServletRequest param) {
        param.getParameterMap().forEach((s, strings) -> {
            log.info("Key: {}", s);
            log.info("Value: {}", strings[0]);
        });
        return ServerResponse.success();
    }

}
