package com.idle.oauth.api.kakao;

import com.idle.oauth.api.OauthLoginApi;
import com.idle.oauth.api.dto.ResponseToken;
import com.idle.oauth.api.dto.ResponseUserId;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class MockKaKaoLoginApi implements OauthLoginApi {

    private String refreshToken;

    @Override
    public ResponseToken getToken(String code) {
        return new ResponseToken(
                "Bearer", "accessToken", "refreshToken", 1, 10
        );
    }

    @Override
    public ResponseUserId getMember(String type, String accessToken) {
        return new ResponseUserId(1L);
    }

    @Override
    public ResponseToken tokenReissue(String refreshToken) {
        return new ResponseToken(
                "Bearer", "newAccessToken", this.refreshToken, 1, 10
        );
    }
}
