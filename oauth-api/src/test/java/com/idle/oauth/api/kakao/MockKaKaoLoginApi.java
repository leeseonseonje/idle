package com.idle.oauth.api.kakao;

import com.idle.oauth.api.dto.ResponseKakaoToken;
import com.idle.oauth.api.dto.ResponseKakaoUser;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class MockKaKaoLoginApi implements KakaoLoginApi {

    private String refreshToken;

    @Override
    public ResponseKakaoToken getToken(String code) {
        return new ResponseKakaoToken(
                "Bearer", "accessToken", "refreshToken", 1, 10
        );
    }

    @Override
    public ResponseKakaoUser getMember(String type, String accessToken) {
        return new ResponseKakaoUser(1L);
    }

    @Override
    public ResponseKakaoToken tokenReissue(String refreshToken) {
        return new ResponseKakaoToken(
                "Bearer", "newAccessToken", this.refreshToken, 1, 10
        );
    }
}
