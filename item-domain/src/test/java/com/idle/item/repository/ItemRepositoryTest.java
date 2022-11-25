package com.idle.item.repository;

import com.idle.item.ItemFactory;
import com.idle.item.domain.Item;
import com.idle.member.Member;
import com.idle.member.repository.MemberRepository;
import com.idle.weapon.domain.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static com.idle.weapon.domain.Grade.*;
import static com.idle.weapon.domain.Name.*;

@DataJpaTest

class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void init() {
        Member member = Member.of("email");
        memberRepository.save(member);

        Item normal = Item.of(member, Weapon.of(SWORD, NORMAL));
        Item rare = Item.of(member, Weapon.of(SWORD, RARE));
        Item epic = Item.of(member, Weapon.of(SWORD, EPIC));
        Item unique = Item.of(member, Weapon.of(SWORD, UNIQUE));

        itemRepository.save(unique);
        itemRepository.save(rare);
        itemRepository.save(normal);
        itemRepository.save(epic);
    }
    @Test
    void item_ids_find() {
        List<Item> items = itemRepository.findByIds(List.of(3L, 4L, 5L, 6L), Sort.by(Sort.Direction.ASC, "weapon.grade"));
        for (Item item : items) {
            System.out.println(item.getWeapon().getGrade());
        }
    }
}