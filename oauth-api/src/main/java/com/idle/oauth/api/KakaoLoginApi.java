package com.idle.oauth.api;

import com.idle.oauth.api.dto.KakaoProperties;
import com.idle.oauth.api.dto.OauthRequestDataGenerator;
import com.idle.oauth.api.dto.ResponseToken;
import com.idle.oauth.api.dto.ResponseUserId;
import com.idle.oauth.exception.ExpiredAccessTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class KakaoLoginApi implements OauthLoginApi {

    private final KakaoProperties properties;

    private WebClient webClient = WebClient.create();

    @Override
    public ResponseToken getToken(String code) {
        MultiValueMap<String, String> form = OauthRequestDataGenerator
                .generateTokenIssueKakaoForm(
                        properties.getGrant(),
                        properties.getClientId(),
                        properties.getClientSecret(),
                        properties.getRedirectUri(),
                        code);

        return this.webClient.post()
                .uri("https://kauth.kakao.com/oauth/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(form))
                .retrieve()
                .bodyToMono(ResponseToken.class)
                .block();
    }

    @Override
    public ResponseUserId getMember(String type, String accessToken) {
        return this.webClient.get()
                .uri("https://kapi.kakao.com/v2/user/me")
                .header("Authorization", type + " " + accessToken)
                .retrieve()
                .onStatus(httpStatus -> {
                    if (httpStatus.value() == 401) {
                        throw new ExpiredAccessTokenException("토큰 만료");
                    }
                    return false;
                }, error -> Mono.error(new RuntimeException()))
                .bodyToMono(ResponseUserId.class)
                .block();
    }

    @Override
    public ResponseToken tokenReissue(String refreshToken) {
        MultiValueMap<String, String> form = OauthRequestDataGenerator
                .generateTokenReissueKakaoForm("refresh_token",
                        properties.getClientId(),
                        properties.getClientSecret(),
                        refreshToken);

        return this.webClient.post()
                .uri("https://kauth.kakao.com/oauth/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(form))
                .retrieve()
                .bodyToMono(ResponseToken.class)
                .block();
    }
}
