package com.idle.oauth.service;

import com.idle.member.Member;
import com.idle.oauth.api.kakao.KakaoLoginApi;
import com.idle.oauth.api.dto.ResponseKakaoToken;
import com.idle.oauth.api.dto.ResponseKakaoUser;
import com.idle.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class KakaoLoginService {

    private final KakaoLoginApi kakaoLoginApi;

    private final MemberRepository memberRepository;

    public Member kakaoLogin(String code) {
        ResponseKakaoToken response = kakaoLoginApi.getToken(code);

        ResponseKakaoUser getMember = kakaoLoginApi.getMember(response.tokenType(), response.accessToken());
        return memberRepository.findByOauthId(getMember.oauthId())
                .orElseGet(
                        () -> memberRepository.save(
                                Member.newMember(getMember.oauthId(), response.accessToken(), response.refreshToken())
                        )
                );
    }
}
