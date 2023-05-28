package com.idle.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idle.api.controller.dto.request.RequestNamingDto;
import com.idle.api.controller.dto.response.ResponseMemberDto;
import com.idle.item.service.dto.GradeUpDto;
import com.idle.member.service.MemberService;
import com.idle.oauth.api.OauthLoginApi;
import com.idle.weapon.domain.Grade;
import com.idle.weapon.domain.Name;
import com.idle.weapon.domain.Weapon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

@WebMvcTest(MemberController.class)
@AutoConfigureRestDocs
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    MemberService memberService;

    @MockBean
    OauthLoginApi oauthLoginApi;

    @Test
    void member_naming() throws Exception {
        RequestNamingDto request = new RequestNamingDto(1L, "nickname");
        String requestBody = objectMapper.writeValueAsString(request);

        given(memberService.naming(1L, "nickname"))
                .willReturn("nickname");

        this.mockMvc.perform(post("/members/nickname")
                        .content(requestBody)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("member-naming",
                        requestFields(
                                fieldWithPath("memberId").description("회원 ID"),
                                fieldWithPath("nickname").description("설정할 닉네임")
                        ),
                        responseBody()
                ));
    }

    @Test
    void member_logout() throws Exception {
        this.mockMvc.perform(post("/members/logout/{memberId}", 1))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("member-logout",
                        pathParameters(
                                parameterWithName("memberId").description("회원 ID")
                        )
                ));
    }
}