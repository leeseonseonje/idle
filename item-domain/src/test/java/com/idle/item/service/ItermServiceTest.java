package com.idle.item.service;

import com.idle.item.Item;
import com.idle.item.repository.ItemRepository;
import com.idle.member.Member;
import com.idle.weapon.domain.Grade;
import com.idle.weapon.domain.Weapon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class ItermServiceTest {

    @Autowired
    ItemRepository itemRepository;

    ItemService sut;

    @Test
    @DisplayName("item을 조회해서 업그레이드")
    void findByItemId_upgrade() {
        sut = new ItemService(itemRepository);
        Item item = Item.of(Member.of("eamil"), Weapon.of("검", Grade.NORMAL));
        Item savedItem = itemRepository.save(item);
        savedItem.getMember().getMoney().plusAmount(1000);

        sut.upgrade(savedItem.getId());

        assertThat(savedItem.getUpgrade()).isEqualTo(1);
        assertThat(savedItem.getMember().getMoney().getAmount()).isEqualTo(0);
    }
}