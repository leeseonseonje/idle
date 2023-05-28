package com.idle.api.controller;

import com.idle.api.controller.dto.request.RequestItemSynthesisDto;
import com.idle.item.service.ItemService;
import com.idle.item.service.dto.GradeUpDto;
import com.idle.item.service.dto.ResponseItemDto;
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
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

@WebMvcTest(ItemController.class)
@AutoConfigureRestDocs
public class ItemControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ItemService itemService;

    @MockBean
    OauthLoginApi oauthLoginApi;

    @Test
    void item_upgrade() throws Exception {
        given(itemService.upgrade(1L))
                .willReturn(new ResponseItemDto(1L, Weapon.of(Name.SWORD, Grade.UNIQUE), 10, 0));

        this.mockMvc.perform(post("/items/upgrade/{itemId}", 1))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("item_upgrade",
                        pathParameters(
                                parameterWithName("itemId").description("아이템 ID")
                        ),
                        responseFields(
                                fieldWithPath("itemId").description("아이템 ID"),
                                fieldWithPath("weapon.name").description("아이템 이름"),
                                fieldWithPath("weapon.grade").description("아이템 등급"),
                                fieldWithPath("upgrade").description("업그레이드 횟수"),
                                fieldWithPath("star").description("별 횟수")
                        )
                ));
    }

    @Test
    void item_grade_up() throws Exception {
        given(itemService.gradeUp(1L))
                .willReturn(new GradeUpDto(1L, Weapon.of(Name.SWORD, Grade.UNIQUE), 10));

        this.mockMvc.perform(post("/items/grade/{itemId}", 1))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("item_grade_up",
                        pathParameters(
                                parameterWithName("itemId").description("아이템 ID")
                        ),
                        responseFields(
                                fieldWithPath("itemId").description("아이템 ID"),
                                fieldWithPath("weapon.name").description("아이템 이름"),
                                fieldWithPath("weapon.grade").description("아이템 등급"),
                                fieldWithPath("upgrade").description("업그레이드 횟수")
                        )
                ));
    }

    @Test
    void item_synthesis() throws Exception {
        RequestItemSynthesisDto request = new RequestItemSynthesisDto(1L, 2L, 3L, 4L);
        String requestBody = objectMapper.writeValueAsString(request);

        given(itemService.synthesis(List.of(1L, 2L, 3L, 4L)))
                .willReturn(new ResponseItemDto(1L, Weapon.of(Name.SWORD, Grade.LEGENDARY), 10, 0));

        this.mockMvc.perform(post("/items/synthesis")
                .content(requestBody)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("item_synthesis",
                        requestFields(
                                fieldWithPath("normalId").description("노말 등급 아이템 ID"),
                                fieldWithPath("rareId").description("레어 등급 아이템 ID"),
                                fieldWithPath("epicId").description("에픽 등급 아이템 ID"),
                                fieldWithPath("uniqueId").description("유니크 등급 아이템 ID")
                        ),
                        responseFields(
                                fieldWithPath("itemId").description("아이템 ID"),
                                fieldWithPath("weapon.name").description("아이템 이름"),
                                fieldWithPath("weapon.grade").description("아이템 등급"),
                                fieldWithPath("upgrade").description("업그레이드 횟수"),
                                fieldWithPath("star").description("별 횟수")
                        )
                ));
    }

    @Test
    void item_star_up() throws Exception {
        given(itemService.starUp(List.of(1L, 2L)))
                .willReturn(new ResponseItemDto(1L, Weapon.of(Name.SWORD, Grade.LEGENDARY), 10, 1));

        this.mockMvc.perform(post("/items/star/{legendary1}/{legendary2}", 1, 2))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("item_star_up",
                        pathParameters(
                                parameterWithName("legendary1").description("레전더리 아이템1"),
                                parameterWithName("legendary2").description("레전더리 아이템2")
                        ),
                        responseFields(
                                fieldWithPath("itemId").description("아이템 ID"),
                                fieldWithPath("weapon.name").description("아이템 이름"),
                                fieldWithPath("weapon.grade").description("아이템 등급"),
                                fieldWithPath("upgrade").description("업그레이드 횟수"),
                                fieldWithPath("star").description("별 횟수")
                        )
                ));
    }

    @Test
    void item_end() throws Exception {
        given(itemService.end(1L))
                .willReturn(new ResponseItemDto(1L, Weapon.of(Name.SWORD, Grade.END), 0, 0));

        this.mockMvc.perform(post("/items/end/{itemId}", 1))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("item_end",
                        pathParameters(
                                parameterWithName("itemId").description("아이템 ID")
                        ),
                        responseFields(
                                fieldWithPath("itemId").description("아이템 ID"),
                                fieldWithPath("weapon.name").description("아이템 이름"),
                                fieldWithPath("weapon.grade").description("아이템 등급"),
                                fieldWithPath("upgrade").description("업그레이드 횟수"),
                                fieldWithPath("star").description("별 횟수")
                        )
                ));
    }

    @Test
    void item_wearing() throws Exception {
        this.mockMvc.perform(post("/items/wear/{itemId}", 1))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("item_wearing",
                        pathParameters(
                                parameterWithName("itemId").description("아이템 ID")
                        )
                ));
    }
}
