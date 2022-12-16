package com.idle.api.controller.query;

import com.idle.api.controller.sort.ItemsSort;
import com.idle.item.domain.Item;
import com.idle.item.repository.ItemRepository;
import com.idle.member.Member;
import com.idle.weapon.domain.Grade;
import com.idle.weapon.domain.Name;
import com.idle.weapon.domain.Weapon;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ItemQueryController.class)
class ItemQueryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ItemRepository itemRepository;

    @Test
    void member_item_list_order_param() throws Exception {
        String[] condition = ItemsSort.NAME.condition();
        Sort sort = Sort.by(Sort.Direction.DESC, condition);
        BDDMockito.given(itemRepository.findByMemberId(1L, sort))
                .willReturn(List.of(Item.of(Member.of("email"), Weapon.of(Name.SWORD, Grade.NORMAL), 0, 0, false)));

        this.mockMvc.perform(get("/items/1")
                        .param("sort", "GRADE"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}