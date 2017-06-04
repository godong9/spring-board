package com.board.gd.iamport;

import com.board.gd.domain.payment.PaymentInfoDto;
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

    public PaymentInfoDto postSubscribeCustomer(CustomerRequestDto customerRequestDto) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme(iamportScheme)
                .host(iamportHost)
                .path("/subscribe/customers/" + customerRequestDto.getCustomer_uid())
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(JsonUtils.toJson(customerRequestDto, PropertyNamingStrategy.LOWER_CASE), headers);

        ResponseEntity<SubscribeResponseDto> responseEntity = iamportRestTemplate.exchange(uriComponents.toUri(), HttpMethod.POST, entity, SubscribeResponseDto.class);
        return responseEntity.getBody().getResponse();
    }

}
