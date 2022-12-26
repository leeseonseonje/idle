package com.idle.item.repository;

import com.idle.item.domain.Item;
import com.idle.member.Member;
import com.idle.member.repository.MemberRepository;
import com.idle.weapon.domain.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static com.idle.weapon.domain.Grade.*;
import static com.idle.weapon.domain.Name.*;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.data.domain.Sort.*;
import static org.springframework.data.domain.Sort.Direction.*;

@DataJpaTest

class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepository memberRepository;

    List<Long> ids;

    @BeforeEach
    void init() {
        Member member = Member.simple("email");
        memberRepository.save(member);

        Item normal = Item.of(member, Weapon.of(SWORD, NORMAL), 0, 0, false);
        Item rare = Item.of(member, Weapon.of(SWORD, RARE), 0, 0, false);
        Item epic = Item.of(member, Weapon.of(SWORD, EPIC), 0, 0, false);
        Item unique = Item.of(member, Weapon.of(SWORD, UNIQUE), 0, 0, false);

        itemRepository.save(unique);
        itemRepository.save(rare);
        itemRepository.save(normal);
        itemRepository.save(epic);
        ids = List.of(normal.getId(), rare.getId(), epic.getId(), unique.getId());
    }
    @Test
    void item_ids_find() {
        List<Item> items = itemRepository.findByIds(ids, by(ASC, "weapon.grade"));

        assertThat(items).extracting("weapon.grade")
                .containsExactly(NORMAL, RARE, EPIC, UNIQUE);
    }

    @Test
    void order_test() {
        Member member = Member.simple("e");
        memberRepository.save(member);

        Item itemA = Item.of(member, Weapon.of(SWORD, RARE), 1, 1, false);
        Item itemB = Item.of(member, Weapon.of(SPEAR, NORMAL), 31, 4, false);
        Item itemC = Item.of(member, Weapon.of(AXE, EPIC), 26, 1, false);
        Item itemD = Item.of(member, Weapon.of(SWORD, RARE), 125, 1, false);
        itemRepository.save(itemA);
        itemRepository.save(itemB);
        itemRepository.save(itemC);
        itemRepository.save(itemD);
        List<Long> ids = List.of(itemA.getId(), itemB.getId(), itemC.getId(), itemD.getId());
        String[] gradeOrder = {"weapon.grade", "star", "upgrade"};
        String[] nameOrder = {"weapon.name", "weapon.grade", "star", "upgrade"};
        List<Item> byIds = itemRepository.findByIds(ids, by(DESC, nameOrder));
        /*
        * 1. grade, name
        * 2. grade -> name, name -> grade
        * 3. star
        * 4. upgrade
        * */
        for (Item byId : byIds) {
            System.out.println(byId.getId() + " " + byId.getWeapon().getName() + " " + byId.getWeapon().getGrade() + " " + byId.getUpgrade() + " " + byId.getStar());
        }
        String s = "weapon.grade, weapon.name, star, upgrade";

        for (String s1 : s.split(", ")) {
            System.out.println(s1);
        }
    }
}