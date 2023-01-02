package com.idle.oauth.api.dto;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class RequestKakaoTokenReissue {

    private String grantType;

    private String clientId;

    private String clientSecret;

    private String refreshToken;

    public static MultiValueMap<String, String> toForm(
            String grantType, String clientId, String clientSecret, String refreshToken
    ) {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", grantType);
        form.add("client_id", clientId);
        form.add("client_secret", clientSecret);
        form.add("refresh_token", refreshToken);


        return form;
    }
}
