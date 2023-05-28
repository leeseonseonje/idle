package com.idle.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idle.item.service.dto.ResponseItemDto;
import com.idle.member.Member;
import com.idle.member.repository.MemberRepository;
import com.idle.money.domain.Money;
import com.idle.money.service.MoneyService;
import com.idle.oauth.api.OauthLoginApi;
import com.idle.weapon.domain.Grade;
import com.idle.weapon.domain.Name;
import com.idle.weapon.domain.Weapon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

@WebMvcTest(MoneyController.class)
@AutoConfigureRestDocs
class MoneyControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    MemberRepository memberRepository;

    @MockBean
    MoneyService moneyService;

    @MockBean
    OauthLoginApi oauthLoginApi;

    @Test
    void money_put() throws Exception {
        given(memberRepository.findById(1L))
                .willReturn(Optional.of(Member.simple("nickname")));

        given(moneyService.perMinutePutMoney(Money.of(0, LocalDateTime.now()), LocalDateTime.now()))
                .willReturn(1000);

        this.mockMvc.perform(post("/money/{memberId}", 1))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("money_put",
                        pathParameters(
                                parameterWithName("memberId").description("회원 ID")
                        ),
                        responseBody()
                ));
    }
}