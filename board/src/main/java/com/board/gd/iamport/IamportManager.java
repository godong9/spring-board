package com.board.gd.iamport;

import com.board.gd.domain.payment.PaymentInfoDto;
import com.board.gd.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

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

    public PaymentInfoDto postSubscribeCustomer(CustomerDto customerDto) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme(iamportScheme)
                .host(iamportHost)
                .path("/subscribe/customers/" + customerDto.getCustomerUid())
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(JsonUtils.toJson(customerDto), headers);

        ResponseEntity<PaymentInfoDto> responseEntity = iamportRestTemplate.exchange(uriComponents.toUri(), HttpMethod.POST, entity, PaymentInfoDto.class);
        return responseEntity.getBody();
    }

}
