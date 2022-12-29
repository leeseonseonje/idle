package com.idle.oauth.api.kakao;

import com.idle.oauth.api.dto.ResponseKakaoToken;
import com.idle.oauth.api.dto.ResponseKakaoUser;

public class MockKaKaoLoginApi implements KakaoLoginApi {

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
}
