package com.board.gd.domain.payment;

import com.board.gd.domain.user.User;
import com.board.gd.domain.user.UserService;
import com.board.gd.iamport.ChargeRequestDto;
import com.board.gd.iamport.IamportManager;
import com.board.gd.iamport.SubscribeRequestDto;
import com.board.gd.slack.SlackManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;

/**
 * Created by godong9 on 2017. 6. 4..
 */

@Slf4j
@Transactional(readOnly = true)
@Service
public class PaymentService {

    @Autowired
    private PaymentInfoRepository paymentInfoRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentResultRepository paymentResultRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private IamportManager iamportManager;

    @Autowired
    private SlackManager slackManager;

    @Value("${charge.amount}")
    private Double chargeAmount;

    @Value("${charge.name}")
    private String chargeName;

    public PaymentInfo findPaymentInfoByUserId(Long userId) {
        return paymentInfoRepository.findByUserId(userId);
    }

    @Transactional(readOnly = false)
    public PaymentInfo createPaymentInfo(PaymentInfoDto paymentInfoDto) {
        User user = User.builder().id(paymentInfoDto.getUserId()).build();
        PaymentInfo paymentInfo = paymentInfoRepository.findByUserId(user.getId());
        if (!Objects.isNull(paymentInfo)) {
            paymentInfo.setCardName(paymentInfoDto.getCardName());
            paymentInfo.setEnabled(true);
            return paymentInfoRepository.save(paymentInfo);
        }
        return paymentInfoRepository.save(PaymentInfo.builder()
                .cardName(paymentInfoDto.getCardName())
                .customerUid(paymentInfoDto.getCustomerUid())
                .user(user)
                .enabled(true)
                .build());
    }

    @Transactional(readOnly = false)
    public void deletePaymentInfo(String customerUId) {
        Long userId = Long.parseLong(customerUId);
        PaymentInfo paymentInfo = paymentInfoRepository.findByUserId(userId);
        paymentInfoRepository.delete(paymentInfo.getId());
    }

    @Transactional(readOnly = false)
    public void requestSubscribe(SubscribeRequestDto subscribeRequestDto) {
        PaymentInfoDto paymentInfoDto = iamportManager.postSubscribeCustomer(subscribeRequestDto);
        paymentInfoDto.setUserId(Long.parseLong(subscribeRequestDto.getCustomer_uid()));
        createPaymentInfo(paymentInfoDto);
    }

    @Transactional(readOnly = false)
    public void requestUnsubscribe(String customerUid) {
        iamportManager.deleteUnsubscribeCustomer(customerUid);
        deletePaymentInfo(customerUid);
    }

    @Transactional(readOnly = false)
    public String requestPayment(PaymentRequestDto paymentRequestDto) {
        Long userId = paymentRequestDto.getUserId();
        log.info("[PaymentService] Request payment userId: {}", userId);
        slackManager.sendPaymentMessage("[결제 요청] userId: " + userId);

        String customerUid = paymentRequestDto.getUserId().toString();
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setName(chargeName);
        paymentDto.setAmount(chargeAmount);
        paymentDto.setUserId(userId);
        paymentDto.setCustomerUid(customerUid);

        Payment payment = createPayment(paymentDto);

        ChargeRequestDto chargeRequestDto = new ChargeRequestDto();
        chargeRequestDto.setCustomerUid(customerUid);
        chargeRequestDto.setMerchantUid(payment.getId().toString());
        chargeRequestDto.setAmount(chargeAmount);

        PaymentResultDto paymentResultDto = iamportManager.postPaymentCharge(chargeRequestDto);

        StringBuilder paymentResultSb = new StringBuilder();
        paymentResultSb.append("[결제 결과] userId: ");
        paymentResultSb.append(userId);
        paymentResultSb.append(", status: ");
        paymentResultSb.append(paymentResultDto.getPaymentStatus());
        paymentResultSb.append(", message: ");
        paymentResultSb.append(paymentResultDto.getMessage());
        slackManager.sendPaymentMessage(paymentResultSb.toString());

        updatePaymentStatus(payment, paymentResultDto.getPaymentStatus());
        createPaymentResult(paymentResultDto);

        if (paymentResultDto.getPaymentStatus() == PaymentStatus.SUCCESS) {
            log.info("[PaymentService] Renew payment userId: {}", userId);
            slackManager.sendPaymentMessage("[결제 갱신] userId: " + userId);
            userService.upsertPaidRole(userId);
        }
        return paymentResultDto.getMessage();
    }

    @Transactional(readOnly = false)
    public Payment createPayment(PaymentDto paymentDto) {
        User user = User.builder().id(paymentDto.getUserId()).build();
        return paymentRepository.save(Payment.builder()
                .name(paymentDto.getName())
                .amount(paymentDto.getAmount())
                .customerUid(paymentDto.getCustomerUid())
                .user(user)
                .status(PaymentStatus.REQUESTED)
                .build());
    }

    @Transactional(readOnly = false)
    public PaymentResult createPaymentResult(PaymentResultDto paymentResultDto) {
        Payment payment = Payment.builder().id(Long.parseLong(paymentResultDto.getMerchantUid())).build();
        PaymentResult paymentResult = PaymentResult.builder()
                .code(paymentResultDto.getCode())
                .message(paymentResultDto.getMessage())
                .amount(paymentResultDto.getAmount())
                .status(paymentResultDto.getPaymentStatus())
                .payment(payment)
                .impUid(paymentResultDto.getImpUid())
                .pgTid(paymentResultDto.getPgTid())
                .failReason(paymentResultDto.getFailReason())
                .receiptUrl(paymentResultDto.getReceiptUrl())
                .build();

        if (!Objects.isNull(paymentResultDto.getPaidAt())) {
            paymentResult.setPaidAt(new Date(Long.parseLong(paymentResultDto.getPaidAt())));
        }
        return paymentResultRepository.save(paymentResult);
    }

    @Transactional(readOnly = false)
    public void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus) {
        Payment updatedPayment = paymentRepository.findOne(payment.getId());
        updatedPayment.setStatus(paymentStatus);
        paymentRepository.save(updatedPayment);
    }

}
