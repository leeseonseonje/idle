package com.idle.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idle.api.controller.dto.request.RequestStorePurchaseDto;
import com.idle.api.service.StoreApiService;
import com.idle.api.service.dto.StoreItemDto;
import com.idle.oauth.api.OauthLoginApi;
import com.idle.shop.domain.weapon.Product;
import com.idle.weapon.domain.Grade;
import com.idle.weapon.domain.Name;
import com.idle.weapon.domain.Weapon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StoreController.class)
class StoreControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    StoreApiService storeApiService;

    @MockBean
    OauthLoginApi oauthLoginApi;


    @Test
    void weapon_purchase_response() throws Exception {
        RequestStorePurchaseDto request = new RequestStorePurchaseDto(1L, Product.RANDOM_WEAPON_BOX);
        String requestBody = objectMapper.writeValueAsString(request);
        StoreItemDto response = new StoreItemDto(2L, Weapon.of(Name.SWORD, Grade.NORMAL));
        String responseBody = objectMapper.writeValueAsString(response);

        when(storeApiService.weaponPurchase(request.memberId(), request.product())).thenReturn(response);

        this.mockMvc.perform(post("/stores/weapon")
                        .content(requestBody)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody))
                .andDo(print());
    }
}