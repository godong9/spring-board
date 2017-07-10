package com.board.gd.slack;

import com.board.gd.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by gd.godong9 on 2017. 6. 23.
 */

@Slf4j
@Component
public class SlackManager {
    private static final String PAYMENT_WEBHOOK_PATH = "/services/T5XSEDKCZ/B5XPB42SC/VLsh5HzaZWLDaLqu48TUDPbg";

    @Value("${slack.scheme}")
    private String slackScheme;

    @Value("${slack.host}")
    private String slackHost;

    @Autowired
    private RestTemplate restTemplate;

    public void sendPaymentMessage(String message) {
        SlackMessageDto slackMessageDto = new SlackMessageDto();
        slackMessageDto.setText(message);
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme(slackScheme)
                .host(slackHost)
                .path(PAYMENT_WEBHOOK_PATH)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(JsonUtils.toJson(slackMessageDto), headers);

        restTemplate.exchange(uriComponents.toUri(), HttpMethod.POST, entity, String.class);
    }
}
