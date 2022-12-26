package com.idle.api.controller;

import com.idle.api.controller.dto.response.ResponseMemberDto;
import com.idle.member.Member;
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
    public ResponseMemberDto oauth(@RequestParam String code) {
        Member member = memberService.kakaoLogin(code);
        return ResponseMemberDto.toDto(member);
    }
}
