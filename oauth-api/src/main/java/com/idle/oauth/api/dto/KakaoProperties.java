package com.idle.oauth.api.dto;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConfigurationProperties("oauth.kakao")
public class KakaoProperties {

    private String grant;
    private String clientId;
    private String clientSecret;
    private String redirectUri;

    @ConstructorBinding
    public KakaoProperties(String grant, String clientId, String clientSecret, String redirectUri) {
        this.grant = grant;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
    }
}
