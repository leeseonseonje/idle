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

    @Test
    void token_reissue_refresh_token_is_null_not_update() {
        Member member = Member.builder().accessToken("accessToken").refreshToken("refreshToken").build();
        Member savedMember = memberRepository.save(member);
        KakaoLoginService sut = new KakaoLoginService(new MockKaKaoLoginApi(null), memberRepository);

        sut.tokenReissue("accessToken");

        assertThat(savedMember.getAccessToken()).isEqualTo("newAccessToken");
        assertThat(savedMember.getRefreshToken()).isEqualTo("refreshToken");
    }

    @Test
    void refresh_token_reissue_update() {
        Member member = Member.builder().accessToken("accessToken").refreshToken("refreshToken").build();
        Member savedMember = memberRepository.save(member);
        KakaoLoginService sut = new KakaoLoginService(new MockKaKaoLoginApi("newRefreshToken"), memberRepository);

        sut.tokenReissue("accessToken");

        assertThat(savedMember.getAccessToken()).isEqualTo("newAccessToken");
        assertThat(savedMember.getRefreshToken()).isEqualTo("newRefreshToken");
    }
}