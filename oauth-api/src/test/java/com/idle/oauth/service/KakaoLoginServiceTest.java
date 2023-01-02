package com.idle.oauth.service;

import com.idle.member.Member;
import com.idle.member.repository.MemberRepository;
import com.idle.oauth.api.kakao.MockKaKaoLoginApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class KakaoLoginServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void first_login_save() {
        KakaoLoginService sut = new KakaoLoginService(new MockKaKaoLoginApi(), memberRepository);

        Member member = sut.kakaoLogin("code");

        assertThat(member.getOauthId()).isEqualTo(1L);
        assertThat(member.getAccessToken()).isEqualTo("accessToken");
        assertThat(member.getRefreshToken()).isEqualTo("refreshToken");
    }
}