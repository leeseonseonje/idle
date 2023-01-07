package com.idle.api.controller;

import com.idle.api.controller.dto.request.RequestNamingDto;
import com.idle.api.controller.dto.response.ResponseMemberDto;
import com.idle.member.Member;
import com.idle.member.service.MemberService;
import com.idle.oauth.service.KakaoLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final KakaoLoginService kakaoLoginService;

    @PostMapping("/member/nickname")
    public String naming(@RequestBody RequestNamingDto reqeust) {
//        return memberService.naming(reqeust.memberId(), reqeust.nickname());
        throw new RuntimeException("slrspdla");
    }

    @GetMapping("/member/logout/{memberId}")
    public void logout(@PathVariable Long memberId) {
        memberService.logout(memberId);
    }
}
