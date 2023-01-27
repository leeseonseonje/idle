package com.idle.oauth.api.dto;

import lombok.AllArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@AllArgsConstructor
public class OauthRequestDataGenerator {

    public static MultiValueMap<String, String> generateTokenIssueKakaoForm(
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

    public static MultiValueMap<String, String> generateTokenReissueKakaoForm(
            String grantType, String clientId, String clientSecret, String refreshToken
    ) {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", grantType);
        form.add("client_id", clientId);
        form.add("client_secret", clientSecret);
        form.add("redirect_uri", refreshToken);

        return form;
    }
}
