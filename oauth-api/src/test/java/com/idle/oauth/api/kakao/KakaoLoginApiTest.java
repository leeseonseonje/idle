package com.idle.oauth.api.kakao;

import com.idle.oauth.api.dto.ResponseKakaoUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class KakaoLoginApiTest {

    @Autowired
    KakaoLoginApi kakaoLoginApi;

    @Test
    void access_token_expired() {
        //vFcPataoQtKoCXdU7wKq7IGxoaHCbRltchMTZn4DCj11GwAAAYVtusWW
        ResponseKakaoUser member = kakaoLoginApi.getMember("bearer", "vFcPataoQtKoCXdU7wKq7IGxoaHCbRltchMTZn4DCj11GwAAAYVtusWW");
        System.out.println(member.id());
    }
}
