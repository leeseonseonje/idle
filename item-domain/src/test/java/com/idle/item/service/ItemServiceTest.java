package com.idle.item.service;

import com.idle.item.domain.Item;
import com.idle.item.repository.ItemRepository;
import com.idle.member.Member;
import com.idle.member.repository.MemberRepository;
import com.idle.weapon.domain.Name;
import com.idle.weapon.domain.Grade;
import com.idle.weapon.domain.Weapon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static com.idle.weapon.domain.Grade.*;
import static com.idle.weapon.domain.Grade.UNIQUE;
import static com.idle.weapon.domain.Name.SWORD;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class ItemServiceTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("item을 조회해서 업그레이드")
    void findByItemId_upgrade() {
        ItemService sut = new ItemService(itemRepository);
        Member member = createMember(1000);
        Item item = Item.of(member, Weapon.of(Name.SWORD, Grade.NORMAL));
        Item savedItem = itemRepository.save(item);
        savedItem.getMember().getMoney().plusAmount(1000);

        sut.upgrade(savedItem.getId());

        assertThat(savedItem.getUpgrade()).isEqualTo(1);
        assertThat(savedItem.getMember().getMoney().getAmount()).isEqualTo(0);
    }

    @Test
    @DisplayName("4개(노말, 레어, 에픽, 유니크)무기를 찾아 검증 레전더리 무기 저장")
    void synthesis_legendary_weapon() {
        ItemService sut = new ItemService(itemRepository);
        Member member = createMember(100000);
        List<Item> items = upgradeWeaponInit(member);
        itemRepository.saveAll(items);

        Item item = sut.synthesis(List.of(3L, 4L, 5L, 6L));

        assertThat(item.getWeapon().getGrade()).isEqualTo(LEGENDARY);
    }

    private Member createMember(int amount) {
        Member member = memberRepository.save(Member.of("email"));
        member.getMoney().plusAmount(amount);
        return member;
    }

    private List<Item> upgradeWeaponInit(Member member) {
        Item normal = Item.of(member, Weapon.of(SWORD, NORMAL));
        Item rare = Item.of(member, Weapon.of(SWORD, RARE));
        Item epic = Item.of(member, Weapon.of(SWORD, EPIC));
        Item unique = Item.of(member, Weapon.of(SWORD, UNIQUE));
        List<Item> items = List.of(normal, rare, epic, unique);
        for (Item item : items) {
            for (int i = 0; i < 100; i++) {
                item.upgrade(member.getMoney());
            }
        }
        return items;
    }
}