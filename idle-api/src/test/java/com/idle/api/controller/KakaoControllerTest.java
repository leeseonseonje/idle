package com.idle.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idle.member.Member;
import com.idle.oauth.api.OauthLoginApi;
import com.idle.oauth.service.KakaoLoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

@WebMvcTest(KakaoController.class)
@AutoConfigureRestDocs
class KakaoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    KakaoLoginService kakaoLoginService;

    @MockBean
    OauthLoginApi oauthLoginApi;

    @Test
    void oauth_redirect_uri() throws Exception {
        this.mockMvc.perform(get("/oauth/redirect")
                        .param("code", "code"))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("oauth-redirect-uri",
                        requestParameters(
                                parameterWithName("code").description("oauth code")
                        )));
    }

    @Test
    void kakao_login() throws Exception {
        given(kakaoLoginService.kakaoLogin("code"))
                .willReturn(Member.simple("nickname"));

        this.mockMvc.perform(get("/oauth/kakao")
                        .param("code", "code"))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("oauth-kakao-login",
                        requestParameters(
                                parameterWithName("code").description("oauth code")
                        ),
                        responseFields(
                                fieldWithPath("memberId").description("회원 ID"),
                                fieldWithPath("nickname").description("닉네임"),
                                fieldWithPath("accessToken").description("액세스 토큰")
                        )
                        ));
    }

    @Test
    void token_reissue() throws Exception {
        given(kakaoLoginService.tokenReissue("acessToken"))
                .willReturn("accessToken");

        this.mockMvc.perform(get("/oauth/kakao/reissue/{accessToken}", "accessToken"))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("token-reissue",
                        pathParameters(
                                parameterWithName("accessToken").description("액세스 토큰")
                        )
                ));
    }
}