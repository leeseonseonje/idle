package com.idle.member.service;

import com.idle.member.Member;
import com.idle.member.api.KakaoLoginApi;
import com.idle.member.api.dto.ResponseKakaoToken;
import com.idle.member.api.dto.ResponseKakaoUser;
import com.idle.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final KakaoLoginApi kakaoLoginApi;

    private final MemberRepository memberRepository;

    public Member kakaoLogin(String code) {
        ResponseKakaoToken response = kakaoLoginApi.getToken(code);

        ResponseKakaoUser getMember = kakaoLoginApi.getMember("Bearer", response.accessToken());
        return memberRepository.findById(getMember.id())
                .orElseGet(
                        () -> memberRepository.save(
                                Member.newMember(getMember.id(), response.accessToken(), response.refreshToken())
                        )
                );
    }
}
