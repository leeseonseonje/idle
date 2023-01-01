package com.idle.api.controller;

import com.idle.api.controller.dto.request.RequestNamingDto;
import com.idle.api.controller.dto.response.ResponseMemberDto;
import com.idle.member.Member;
import com.idle.member.service.MemberService;
import com.idle.oauth.service.KakaoLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
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

    @PostMapping("/nickname")
    public String naming(@RequestBody RequestNamingDto reqeust) {
        return memberService.naming(reqeust.memberId(), reqeust.nickname());
    }
}
