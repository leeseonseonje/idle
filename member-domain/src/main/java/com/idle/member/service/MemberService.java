package com.idle.member.service;

import com.idle.member.api.KakaoLoginApi;
import com.idle.member.api.dto.ResponseKakaoToken;
import com.idle.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final KakaoLoginApi kakaoLoginApi;

    private final MemberRepository memberRepository;

    public ResponseKakaoToken kakaoLogin(String code) {
        return kakaoLoginApi.getToken(code);
    }

    public ResponseKakaoToken getKakaoMember(String code) {
//        memberRepository.findByAccessToken()
    }
}
