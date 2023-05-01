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
        ResponseToken token = kakaoLoginApi.getToken(code);
        ResponseUserId kakaoUser = kakaoLoginApi.getMember(token.tokenType(), token.accessToken());
        Optional<Member> optionalMember = memberRepository.findByOauthId(kakaoUser.id());

        if (optionalMember.isPresent()) {
            return login(token, optionalMember);
        }
        return firstLogin(token, kakaoUser);
    }

    private Member firstLogin(ResponseToken token, ResponseUserId kakaoUser) {
        return memberRepository.save(
                Member.firstLogin(kakaoUser.id(), token.accessToken(), token.refreshToken()));
    }

    private Member login(ResponseToken token, Optional<Member> optionalMember) {
        Member findMember = optionalMember.get();
        findMember.login(token.accessToken(), token.refreshToken());
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
