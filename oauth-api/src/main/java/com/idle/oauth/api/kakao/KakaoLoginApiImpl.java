package com.idle.oauth.api.kakao;

import com.idle.oauth.api.dto.RequestKakaoToken;
import com.idle.oauth.api.dto.ResponseKakaoToken;
import com.idle.oauth.api.dto.ResponseKakaoUser;
import com.idle.oauth.exception.ExpiredAccessTokenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class KakaoLoginApiImpl implements KakaoLoginApi {

    @Value("${oauth.kakao.grant}")
    private String grantType;

    @Value("${oauth.kakao.client_id}")
    private String clientId;

    @Value("${oauth.kakao.client_secret}")
    private String clientSecret;

    @Value("${oauth.kakao.redirect_uri}")
    private String redirectUri;

    @Override
    public ResponseKakaoToken getToken(String code) {
        MultiValueMap<String, String> form = RequestKakaoToken
                .toForm(grantType, clientId, clientSecret, redirectUri, code);

        WebClient webClient = WebClient.create();
        return webClient.post()
                .uri("https://kauth.kakao.com/oauth/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(form))
                .retrieve()
                .bodyToMono(ResponseKakaoToken.class)
                .block();
    }

    @Override
    public ResponseKakaoUser getMember(String type, String accessToken) {
        WebClient webClient = WebClient.create();
        return webClient.get()
                .uri("https://kapi.kakao.com/v2/user/me")
                .header("Authorization", type + " " + accessToken)
                .retrieve()
                .onStatus(httpStatus -> {
                    System.out.println(httpStatus.value());
                    if (httpStatus.value() == 401) {
                        throw new ExpiredAccessTokenException("토큰 만료");
                    }
                    return false;
                }, error -> Mono.error(new RuntimeException()))
                .bodyToMono(ResponseKakaoUser.class)
                .block();
    }
}
