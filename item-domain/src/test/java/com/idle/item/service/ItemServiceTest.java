package com.idle.item.service;

import com.idle.item.domain.Item;
import com.idle.item.repository.ItemRepository;
import com.idle.item.service.dto.GradeUpDto;
import com.idle.item.service.dto.ResponseItemDto;
import com.idle.member.Member;
import com.idle.member.repository.MemberRepository;
import com.idle.random.MockRandomGenerator;
import com.idle.weapon.domain.Weapon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.idle.weapon.domain.Grade.*;
import static com.idle.weapon.domain.Grade.UNIQUE;
import static com.idle.weapon.domain.Name.SWORD;
import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
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

        ResponseItemDto result = sut.upgrade(item.getId());

        assertThat(result.upgrade()).isEqualTo(1);
        assertThat(item.getMember().getMoney().getAmount()).isEqualTo(0);
    }

    @Test
    @DisplayName("사용자 아이템을 조회하여 등급 업")
    void user_item_grade_up() {
        ItemService sut = new ItemService(itemRepository, new MockRandomGenerator(999));
        Member member = createMember(1000);
        Item item = createItem(member, Weapon.of(SWORD, EPIC));

        GradeUpDto result = sut.gradeUp(item.getId());

        assertThat(result.weapon().getGrade()).isEqualTo(UNIQUE);
    }

    @Test
    @DisplayName("4개(노말, 레어, 에픽, 유니크)무기를 찾아 검증 레전더리 무기 저장")
    void synthesis_legendary_weapon() {
        ItemService sut = new ItemService(itemRepository, new MockRandomGenerator(0));
        Member member = createMember(1000000);
        List<Long> ids = upgradeWeaponInit(member);

        ResponseItemDto result = sut.synthesis(ids);

        assertThat(result.weapon().getGrade()).isEqualTo(LEGENDARY);
    }

    private List<Long> upgradeWeaponInit(Member member) {
        Item normal = Item.of(member, Weapon.of(SWORD, NORMAL), 100, 0, false);
        Item rare = Item.of(member, Weapon.of(SWORD, RARE), 100, 0, false);
        Item epic = Item.of(member, Weapon.of(SWORD, EPIC), 100, 0, false);
        Item unique = Item.of(member, Weapon.of(SWORD, UNIQUE), 100, 0, false);
        List<Item> items = itemRepository.saveAll(List.of(normal, rare, epic, unique));
        return items.stream().map(Item::getId).collect(toList());
    }

    @Test
    @DisplayName("레전더리 무기 두개를 합성하면 별이 증가한다. 하나의 무기는 삭제된다.")
    public void legendary_plus_legendary_star_up() {
        ItemService sut = new ItemService(itemRepository, null);
        Member member = createMember(1000);
        Item legendary1 = createItem(member, Weapon.of(SWORD, LEGENDARY));
        Item legendary2 = createItem(member, Weapon.of(SWORD, LEGENDARY));
        legendary2.upgrade(member.getMoney());

        ResponseItemDto result = sut.starUp(List.of(legendary1.getId(), legendary2.getId()));

        assertThat(result.star()).isEqualTo(1);
        assertThat(result.upgrade()).isEqualTo(1);
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
            item.starUp(createItem(member, Weapon.of(SWORD, LEGENDARY)));
        }

        ResponseItemDto result = sut.end(item.getId());

        assertThat(result.weapon().getGrade()).isEqualTo(END);
        assertThat(item.getMember().getMoney().getAmount()).isEqualTo(0);
    }

    private Member createMember(int amount) {
        Member member = memberRepository.save(Member.of("email"));
        member.getMoney().amountIncrease(amount);
        return member;
    }

    private Item createItem(Member member, Weapon weapon) {
        Item item = Item.of(member, weapon, 0, 0, false);
        return itemRepository.save(item);
    }
}