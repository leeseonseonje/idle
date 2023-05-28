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
public class KakaoController {

    private final KakaoLoginService kakaoLoginService;

    @GetMapping("/redirect")
    public void redirect(@RequestParam String code) {
    }

    @GetMapping("/kakao")
    public ResponseMemberDto kakaoLogin(@RequestParam String code) {
        Member member = kakaoLoginService.kakaoLogin(code);
        ResponseMemberDto result = ResponseMemberDto.createDto(member);
        return result;
    }

    @GetMapping("/kakao/reissue/{accessToken}")
    public String reissue(@PathVariable String accessToken) {
        return kakaoLoginService.tokenReissue(accessToken);
    }

    @GetMapping("/kakao/valid/token/{accessToken}")
    public void tokenValidation(@PathVariable String accessToken) {
        System.out.println(accessToken);
        kakaoLoginService.tokenValidation(accessToken);
    }
}
