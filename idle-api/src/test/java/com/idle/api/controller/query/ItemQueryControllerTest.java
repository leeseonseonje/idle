package com.idle.api.controller.query;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idle.api.controller.dto.response.ResponseItemDto;
import com.idle.api.controller.query.sort.ItemsSort;
import com.idle.item.domain.Item;
import com.idle.item.repository.ItemRepository;
import com.idle.member.Member;
import com.idle.oauth.api.OauthLoginApi;
import com.idle.weapon.domain.Grade;
import com.idle.weapon.domain.Name;
import com.idle.weapon.domain.Weapon;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ItemQueryController.class)
@AutoConfigureRestDocs
class ItemQueryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ItemRepository itemRepository;

    @MockBean
    OauthLoginApi oauthLoginApi;

    @Test
    void member_item_list_order_param() throws Exception {
        String[] condition = ItemsSort.NAME.conditions();
        Sort sort = Sort.by(Sort.Direction.DESC, condition);
        given(itemRepository.findByMemberId(1L, sort))
                .willReturn(List.of(Item.of(Member.simple("email"), Weapon.of(Name.SWORD, Grade.NORMAL), 0, 0, false)));
        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/members/{memberId}/items", 1)
                        .param("sort", "NAME"))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("items",
                        pathParameters(
                                parameterWithName("memberId").description("회원 식별자")
                        ),
                        responseFields(
                                fieldWithPath("[].itemId").description("보유중인 ID"),
                                fieldWithPath("[].weapon.name").description("아이템 이름"),
                                fieldWithPath("[].weapon.grade").description("아이템 등급"),
                                fieldWithPath("[].upgrade").description("업그레이드 횟수"),
                                fieldWithPath("[].star").description("별 횟수"),
                                fieldWithPath("[].isWear").description("장착 여부")
                        )
                ));
    }

    @Test
    void item_details_information() throws Exception {
        given(itemRepository.findById(1L))
                .willReturn(Optional.of(Item.of(Member.simple("email"), Weapon.of(Name.SWORD, Grade.NORMAL), 0, 0, false)));
        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/items/{itemId}", 1))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("items-details",
                        pathParameters(
                                parameterWithName("itemId").description("아이템 ID")
                        ),
                        responseFields(
                                fieldWithPath("itemId").description("아이템 ID"),
                                fieldWithPath("weapon.name").description("아이템 이름"),
                                fieldWithPath("weapon.grade").description("아이템 등급"),
                                fieldWithPath("upgrade").description("업그레이드 횟수"),
                                fieldWithPath("star").description("별 횟수"),
                                fieldWithPath("isWear").description("장착 여부")
                        )
                ));
    }

    @Test
    void wear_items() throws Exception {
        given(itemRepository.findByWearingItems(1L))
                .willReturn(Optional.of(Item.of(Member.simple("email"), Weapon.of(Name.SWORD, Grade.NORMAL), 0, 0, false)));
        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/members/{memberId}/items/wear", 1))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("wear-items",
                        pathParameters(
                                parameterWithName("memberId").description("회원 식별자")
                        ),
                        responseFields(
                                fieldWithPath("itemId").description("아이템 ID"),
                                fieldWithPath("weapon.name").description("아이템 이름"),
                                fieldWithPath("weapon.grade").description("아이템 등급"),
                                fieldWithPath("upgrade").description("업그레이드 횟수"),
                                fieldWithPath("star").description("별 횟수"),
                                fieldWithPath("isWear").description("장착 여부")
                        )
                ));
    }
}