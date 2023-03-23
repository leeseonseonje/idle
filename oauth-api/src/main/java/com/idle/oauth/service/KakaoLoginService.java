package com.idle.oauth.service;

import com.idle.member.Member;
import com.idle.oauth.api.OauthLoginApi;
import com.idle.oauth.api.dto.ResponseToken;
import com.idle.oauth.api.dto.ResponseUserId;
import com.idle.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class KakaoLoginService {

    private final OauthLoginApi kakaoLoginApi;

    private final MemberRepository memberRepository;

    public Member kakaoLogin(String code) {
        ResponseToken response = kakaoLoginApi.getToken(code);
        ResponseUserId kakaoUser = kakaoLoginApi.getMember(response.tokenType(), response.accessToken());
        Optional<Member> optionalMember = memberRepository.findByOauthId(kakaoUser.id());
        if (optionalMember.isPresent()) {
            return login(response, optionalMember);
        } else {
            return firstLogin(response, kakaoUser);
        }
    }

    private Member firstLogin(ResponseToken response, ResponseUserId kakaoUser) {
        return memberRepository.save(
                Member.firstLogin(kakaoUser.id(), response.accessToken(), response.refreshToken()));
    }

    private Member login(ResponseToken response, Optional<Member> optionalMember) {
        Member findMember = optionalMember.get();
        findMember.login(response.accessToken(), response.refreshToken());
        return findMember;
    }

    public void tokenValidation(String accessToken) {
        kakaoLoginApi.getMember("Bearer", accessToken);
    }

    public String tokenReissue(String accessToken) {
        Member member = memberRepository.findByAccessToken(accessToken)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        ResponseToken response = kakaoLoginApi.tokenReissue(member.getRefreshToken());

        member.tokenReissue(response.accessToken(), response.refreshToken());

        return member.getAccessToken();
    }
}
