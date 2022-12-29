package com.idle.oauth.api.kakao;

import com.idle.oauth.api.dto.ResponseKakaoToken;
import com.idle.oauth.api.dto.ResponseKakaoUser;

public interface KakaoLoginApi {

    ResponseKakaoToken getToken(String code);

    ResponseKakaoUser getMember(String type, String accessToken);
}
