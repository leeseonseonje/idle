package com.idle.api.controller;

import com.idle.member.api.dto.ResponseKakaoToken;
import com.idle.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/oauth")
    public ResponseKakaoToken oauth(@RequestParam String code) {
        return memberService.kakaoLogin(code);
    }
}
