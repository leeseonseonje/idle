package com.idle.api.controller;

import com.idle.api.controller.dto.response.ResponseMemberDto;
import com.idle.member.Member;
import com.idle.oauth.service.KakaoLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final KakaoLoginService kakaoLoginService;

    @GetMapping("/oauth/redirect")
    public void redirect(@RequestParam String code, HttpServletRequest request) {
//        System.out.println("##code = "  + code);
//        System.out.println(request.getHeader("Authorization"));
    }

    @GetMapping("/oauth/kakao")
    public ResponseMemberDto kakaoLogin(@RequestParam String code) {
        Member member = kakaoLoginService.kakaoLogin(code);
        return ResponseMemberDto.toDto(member);
    }

    @GetMapping("/oauth/kakao/reissue/{accessToken}")
    public String reissue(@PathVariable String accessToken) {
//        return kakaoLoginService.tokenReissue(accessToken);
        return "reissueAccessToken";
    }
}
