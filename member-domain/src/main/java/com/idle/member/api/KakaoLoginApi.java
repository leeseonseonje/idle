package com.idle.member.api;

import com.idle.member.api.dto.RequestKakaoToken;
import com.idle.member.api.dto.ResponseKakaoToken;
import com.idle.member.api.dto.ResponseKakaoUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class KakaoLoginApi {

    @Value("${oauth.kakao.grant}")
    private String grantType;

    @Value("${oauth.kakao.client_id}")
    private String clientId;

    @Value("${oauth.kakao.client_secret}")
    private String clientSecret;

    @Value("${oauth.kakao.redirect_uri}")
    private String redirectUri;

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

    public void getMember(String type, String accessToken) {
        WebClient webClient = WebClient.create();
        ResponseKakaoUser result = webClient.get()
                .uri("https://kapi.kakao.com/v2/user/me")
                .header("Authorization", type + " " + accessToken)
                .retrieve()
                .bodyToMono(ResponseKakaoUser.class)
                .block();

        System.out.println("result = " + result.id());
    }
}
