package com.idle.api.controller.query;

import com.idle.oauth.api.OauthLoginApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StoreQueryController.class)
@AutoConfigureRestDocs
class StoreQueryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    OauthLoginApi oauthLoginApi;

    @Test
    void store_product_list() throws Exception {
        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/stores/weapon/products")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("store-product-list",
                        responseFields(
                                fieldWithPath("[].name").description("상품 이름"),
                                fieldWithPath("[].price").description("상품 가격")
                        )
                ));
    }
}