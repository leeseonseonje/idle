package com.idle.oauth.api.dto;

import lombok.AllArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@AllArgsConstructor
public class RequestKakaoToken {
    private String grantType;

    private String clientId;

    private String clientSecret;

    private String redirectUri;

    private String code;

    public static MultiValueMap<String, String> toForm(
            String grantType, String clientId, String clientSecret, String redirectUri, String code
    ) {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", grantType);
        form.add("client_id", clientId);
        form.add("client_secret", clientSecret);
        form.add("redirect_uri", redirectUri);
        form.add("code", code);

        return form;
    }
}
