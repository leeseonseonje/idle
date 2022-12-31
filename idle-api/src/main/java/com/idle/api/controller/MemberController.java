package com.idle.api.controller;

import com.idle.api.controller.dto.response.ResponseMemberDto;
import com.idle.member.Member;
import com.idle.oauth.service.KakaoLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final KakaoLoginService kakaoLoginService;

    @GetMapping("/oauth/redirect")
    public void redirect(@RequestParam String code) {
        System.out.println(code);
    }

    @GetMapping("/kakao")
    public ResponseMemberDto oauth(@RequestParam String code) {
        Member member = kakaoLoginService.kakaoLogin(code);
        return ResponseMemberDto.toDto(member);
    }
}
