package com.board.gd.payment;

import com.board.gd.response.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by godong9 on 2017. 5. 14..
 */

@Slf4j
@RestController
public class PaymentController {
    @GetMapping("/payment/success")
    public ServerResponse getPaymentSuccess(HttpServletRequest param) {
        log.info(param.toString());
        return ServerResponse.success();
    }

    @GetMapping("/payment/cancel")
    public ServerResponse getPaymentCancel(HttpServletRequest param) {
        log.info(param.toString());
        return ServerResponse.success();
    }

    @GetMapping("/payment/callback")
    public ServerResponse getPaymentCallback(HttpServletRequest param) {
        log.info(param.toString());
        return ServerResponse.success();
    }

}
