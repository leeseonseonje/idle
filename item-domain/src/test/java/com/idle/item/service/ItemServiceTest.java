package com.idle.item.service;

import com.idle.item.domain.Item;
import com.idle.item.repository.ItemRepository;
import com.idle.member.Member;
import com.idle.member.repository.MemberRepository;
import com.idle.random.MockRandomGenerator;
import com.idle.weapon.domain.Weapon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.NoSuchElementException;

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
        ItemService sut = new ItemService(itemRepository, new MockRandomGenerator(0));
        Member member = createMember(1000);
        Item item = createItem(member, Weapon.of(SWORD, NORMAL));

        sut.upgrade(item.getId());

        assertThat(item.getUpgrade()).isEqualTo(1);
        assertThat(item.getMember().getMoney().getAmount()).isEqualTo(0);
    }

    @Test
    @DisplayName("사용자 아이템을 조회하여 등급 업")
    void user_item_grade_up() {
        ItemService sut = new ItemService(itemRepository, new MockRandomGenerator(999));
        Member member = createMember(1000);
        Item item = createItem(member, Weapon.of(SWORD, EPIC));

        sut.gradeUp(item.getId());

        assertThat(item.getWeapon().getGrade()).isEqualTo(UNIQUE);
    }

    @Test
    @DisplayName("4개(노말, 레어, 에픽, 유니크)무기를 찾아 검증 레전더리 무기 저장")
    void synthesis_legendary_weapon() {
        ItemService sut = new ItemService(itemRepository, new MockRandomGenerator(0));
        Member member = createMember(1000000);
        upgradeWeaponInit(member);

        Item item = sut.synthesis(List.of(3L, 4L, 5L, 6L));

        assertThat(item.getWeapon().getGrade()).isEqualTo(LEGENDARY);
    }

    private void upgradeWeaponInit(Member member) {
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
        itemRepository.saveAll(items);
    }

    @Test
    @DisplayName("레전더리 무기 두개를 합성하면 별이 증가한다. 하나의 무기는 삭제된다.")
    public void legendary_plus_legendary_star_up() {
        ItemService sut = new ItemService(itemRepository, null);
        Member member = createMember(1000);
        Item legendary1 = createItem(member, Weapon.of(SWORD, LEGENDARY));
        Item legendary2 = createItem(member, Weapon.of(SWORD, LEGENDARY));
        legendary2.upgrade(member.getMoney());

        sut.legendarySynthesis(List.of(legendary1.getId(), legendary2.getId()));

        assertThat(legendary1.getStar()).isEqualTo(1);
        assertThat(legendary1.getUpgrade()).isEqualTo(1);
        assertThatThrownBy(() -> itemRepository.findById(legendary2.getId()).get())
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("엔드 등급 업그레이드")
    public void grade_up_end() {
        ItemService sut = new ItemService(itemRepository, null);
        Member member = createMember(1000000);
        Item item = createItem(member, Weapon.of(SWORD, LEGENDARY));
        for (int i = 0; i < 10; i++) {
            item.legendarySynthesis(createItem(member, Weapon.of(SWORD, LEGENDARY)));
        }

        sut.end(item.getId());

        assertThat(item.getWeapon().getGrade()).isEqualTo(END);
        assertThat(item.getMember().getMoney().getAmount()).isEqualTo(0);
    }

    private Member createMember(int amount) {
        Member member = memberRepository.save(Member.of("email"));
        member.getMoney().plusAmount(amount);
        return member;
    }

    private Item createItem(Member member, Weapon weapon) {
        Item item = Item.of(member, weapon);
        return itemRepository.save(item);
    }
}