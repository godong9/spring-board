package com.board.gd.domain.payment;

import com.board.gd.domain.user.User;
import com.board.gd.domain.user.UserService;
import com.board.gd.exception.PaymentException;
import com.board.gd.iamport.IamportManager;
import com.board.gd.iamport.SubscribeRequestDto;
import com.board.gd.response.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

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

    /**
     * @api {post} /payments/subscribe Request Post payment subscribe
     * @apiName PostSubscribePayment
     * @apiGroup Payment
     *
     * @apiParam {String} card_number 카드 번호 (ex) 1111-2222-3333-4444)
     * @apiParam {String} expiry 카드 유효기간 (ex) 2020-05)
     * @apiParam {String} birth 생년월일 (ex) 900105)
     * @apiParam {Number} pwd_2digit 패스워드 앞 두자리 (ex) 11)
     *
     * @apiSuccess {Number} status 상태코드
     *
     * @apiUse BadRequestError
     */
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

    @PostMapping("/payments/unsubscribe")
    public ServerResponse postPaymentUnsubscribe() {
        User user = userService.getCurrentUser();
        // TODO: unsubscribe 로직 추가
        PaymentInfo paymentInfo = paymentService.findByUserId(user.getId());
        if (Objects.isNull(paymentInfo) || !paymentInfo.getEnabled()) {
            throw new PaymentException("잘못된 접근입니다.");
        }
        iamportManager.deleteUnsubscribeCustomer(paymentInfo.getCustomerUid());
        paymentService.disablePaymentInfo(user.getId());
        return ServerResponse.success();
    }

    /**
     * @api {get} /payments/charge Request Get payment charge
     * @apiName GetChargePayment
     * @apiDescription 매일 12시에 결제 만료 예정인 유저들 재결제 요청
     * @apiGroup Payment
     *
     * @apiSuccess {Number} status 상태코드
     *
     * @apiUse BadRequestError
     */
    @GetMapping("/payments/charge")
    public ServerResponse getPaymentCharge() {
        // TODO: UserRole에서 PAID이고 만료일이 24시간보다 적게 남은 것들 가져와서
        // TODO: PaymentInfo에 enabled true인지 확인 후 Payment 생성해서 결제 요청
        return ServerResponse.success();
    }
}
