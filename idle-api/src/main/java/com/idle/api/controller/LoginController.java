package com.idle.api.controller;

import com.idle.api.controller.dto.response.ResponseMemberDto;
import com.idle.member.Member;
import com.idle.oauth.service.KakaoLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class LoginController {

    private final KakaoLoginService kakaoLoginService;

    @GetMapping("/redirect")
    public void redirect(@RequestParam String code, HttpServletRequest request) {
//        System.out.println("##code = "  + code);
//        System.out.println(request.getHeader("Authorization"));
    }

    @GetMapping("/kakao")
    public ResponseMemberDto kakaoLogin(@RequestParam String code) {
        Member member = kakaoLoginService.kakaoLogin(code);
        return ResponseMemberDto.toDto(member);
    }

    @GetMapping("/kakao/reissue/{accessToken}")
    public String reissue(@PathVariable String accessToken) {
//        return kakaoLoginService.tokenReissue(accessToken);
        return "reissueAccessToken";
    }
}
