package com.board.gd.iamport;

import com.board.gd.utils.JsonUtils;
import com.google.common.base.MoreObjects;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by godong9 on 2017. 6. 4..
 */

@Slf4j
@Component
public class IamportAuthInterceptor implements ClientHttpRequestInterceptor {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${iamport.api-key}")
    private String apiKey;

    @Value("${iamport.api-secret}")
    private String apiSecret;

    @Value("${iamport.scheme}")
    private String iamportScheme;

    @Value("${iamport.host}")
    private String iamportHost;

    private static final int TOKEN_VALID_MINUTES = 20;
    private static final String HEADER_Authorization = "Authorization";
    private static final String HEADER_X_ImpTokenHeader = "X-ImpTokenHeader";

    private AtomicReference<String> accessToken = new AtomicReference<>();
    private AtomicReference<LocalDateTime> lastCachedDateTime = new AtomicReference<>();

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws HttpServerErrorException, IOException {
        if (isAccessTokenExpired()) {
            log.info("[Metakage] AuthToken is expired!");
            lastCachedDateTime.set(LocalDateTime.now()); // 캐시타임 갱신
            accessToken.set(auth());
        }
        HttpHeaders reqHeaders = request.getHeaders();
        reqHeaders.add(HEADER_Authorization, accessToken.get());
        reqHeaders.add(HEADER_X_ImpTokenHeader, accessToken.get());
        reqHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        return execution.execute(request, body);
    }

    private boolean isAccessTokenExpired() {
        if (accessToken.get() == null) {
            return true;
        }
        lastCachedDateTime.set(MoreObjects.firstNonNull(lastCachedDateTime.get(), LocalDateTime.now()));
        LocalDateTime currentDateTime = LocalDateTime.now();
        return lastCachedDateTime.get().until(currentDateTime, ChronoUnit.MINUTES) > TOKEN_VALID_MINUTES; // 차이가 토큰 유효시간보다 크면 토큰 만료
    }

    private String auth() {

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme(iamportScheme)
                .host(iamportHost)
                .path("/users/getToken")
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        AuthRequestDto authRequestDto = new AuthRequestDto();
        authRequestDto.setImpKey(apiKey);
        authRequestDto.setImpSecret(apiSecret);

        HttpEntity<String> entity = new HttpEntity<>(JsonUtils.toJson(authRequestDto), headers);

        try {
            ResponseEntity<AuthResponseDto> responseEntity = restTemplate.exchange(uriComponents.toUri(), HttpMethod.POST, entity, AuthResponseDto.class);
            AuthResponseDto authResponseDto = responseEntity.getBody();
            return authResponseDto.getResponse().getAccessToken();
        } catch (Exception e) {
            log.error(Throwables.getStackTraceAsString(e));
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
