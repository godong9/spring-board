package com.board.gd.iamport;

import com.board.gd.domain.payment.PaymentInfoDto;
import com.board.gd.domain.payment.PaymentResultDto;
import com.board.gd.domain.payment.PaymentStatus;
import com.board.gd.exception.PaymentException;
import com.board.gd.utils.JsonUtils;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

/**
 * Created by godong9 on 2017. 6. 4..
 */

@Slf4j
@Component
public class IamportManager {
    @Autowired
    private RestTemplate iamportRestTemplate;

    @Value("${iamport.scheme}")
    private String iamportScheme;

    @Value("${iamport.host}")
    private String iamportHost;

    public PaymentInfoDto postSubscribeCustomer(SubscribeRequestDto subscribeRequestDto) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme(iamportScheme)
                .host(iamportHost)
                .path("/subscribe/customers/" + subscribeRequestDto.getCustomer_uid())
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(JsonUtils.toJson(subscribeRequestDto, PropertyNamingStrategy.LOWER_CASE), headers);

        ResponseEntity<SubscribeResponseDto> responseEntity = iamportRestTemplate.exchange(uriComponents.toUri(), HttpMethod.POST, entity, SubscribeResponseDto.class);
        SubscribeResponseDto subscribeResponseDto = responseEntity.getBody();
        log.info("[Iamport] 카드등록 응답 - code: {}, message: {}", subscribeResponseDto.getCode(), subscribeResponseDto.getMessage());
        PaymentInfoDto paymentInfoDto = subscribeResponseDto.getResponse();

        if (Objects.isNull(paymentInfoDto)) {
            log.error("[빌링키 발급 에러] userId: {}, message: {}", subscribeRequestDto.getCustomer_uid(), subscribeResponseDto.getMessage());
            throw new PaymentException("정기결제 등록 중 에러 발생");
        }
        return paymentInfoDto;
    }

    public void deleteUnsubscribeCustomer(String customerUid) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme(iamportScheme)
                .host(iamportHost)
                .path("/subscribe/customers/" + customerUid)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        ResponseEntity<SubscribeResponseDto> responseEntity = iamportRestTemplate.exchange(uriComponents.toUri(), HttpMethod.DELETE, null, SubscribeResponseDto.class);
        SubscribeResponseDto subscribeResponseDto = responseEntity.getBody();

        if (subscribeResponseDto.getCode() != 0) {
            log.error("[빌링키 삭제 에러] userId: {}, message: {}", customerUid, subscribeResponseDto.getMessage());
            throw new PaymentException("정기결제 삭제 중 에러 발생");
        }
    }

    public PaymentResultDto postPaymentCharge(ChargeRequestDto chargeRequestDto) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme(iamportScheme)
                .host(iamportHost)
                .path("/subscribe/payments/again")
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(JsonUtils.toJson(chargeRequestDto), headers);

        ResponseEntity<ChargeResponseDto> responseEntity = iamportRestTemplate.exchange(uriComponents.toUri(), HttpMethod.POST, entity, ChargeResponseDto.class);
        ChargeResponseDto chargeResponseDto = responseEntity.getBody();
        log.info("[Iamport] 결제응답 - code: {}, message: {}", chargeResponseDto.getCode(), chargeResponseDto.getMessage());

        PaymentResultDto paymentResultDto = chargeResponseDto.getResponse();
        if (Objects.isNull(paymentResultDto)) {
            paymentResultDto = new PaymentResultDto();
            paymentResultDto.setMerchantUid(chargeRequestDto.getMerchantUid());
        }
        paymentResultDto.setCode(chargeResponseDto.getCode());
        paymentResultDto.setMessage(chargeResponseDto.getMessage());

        if (chargeResponseDto.getCode() != 0) { // 결제 실패
            log.error("[결제 에러] userId: {}, message: {}", chargeRequestDto.getCustomerUid(), chargeResponseDto.getMessage());
            paymentResultDto.setPaymentStatus(PaymentStatus.FAIL);
        } else {
            paymentResultDto.setPaymentStatus(PaymentStatus.SUCCESS);
        }

        return paymentResultDto;
    }
}
