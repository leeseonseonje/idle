package com.idle.oauth.api.kakao;

import com.idle.oauth.api.OauthLoginApi;
import com.idle.oauth.api.dto.ResponseUserId;
import com.idle.oauth.exception.ExpiredAccessTokenException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class KakaoLoginApiTest {

    @Autowired
    OauthLoginApi kakaoLoginApi;

    @Test
    void access_token_expired() {
        Assertions.assertThatThrownBy(() -> kakaoLoginApi.getMember("bearer", "accessToken"))
                .isInstanceOf(ExpiredAccessTokenException.class);
    }
}
