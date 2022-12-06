package com.idle.item.domain.test;

import com.idle.item.domain.Item;
import com.idle.item.exception.SynthesisFailedException;
import com.idle.money.domain.Money;
import com.idle.weapon.domain.Weapon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.idle.item.domain.ItemFactory.*;
import static com.idle.weapon.domain.Grade.*;
import static com.idle.weapon.domain.Name.*;
import static org.assertj.core.api.Assertions.*;

public class ItemStarUpTest {

    @Test
    @DisplayName("같은 종류의 레전더리 등급 무기를 2개 합성하면 별 증가 업그레이드는 합쳐짐")
    void legendary_plus_legendary_star_up() {
        Money money = createMoney(10000000);
        Item legendary1 = createItem(Weapon.of(SWORD, LEGENDARY), 100, 0, false);
        Item legendary2 = createItem(Weapon.of(SWORD, LEGENDARY), 100, 0, false);

        legendary1.starUp(legendary2);

        assertThat(legendary1.getStar()).isEqualTo(1);
        assertThat(legendary1.getUpgrade()).isEqualTo(200);
    }

    @Test
    @DisplayName("등급이 다르면 예외 발생")
    void other_grade_fail() {
        Item legendary = createItem(Weapon.of(SWORD, LEGENDARY), 0, 0, false);
        Item unique = createItem(Weapon.of(SWORD, UNIQUE), 0, 0, false);

        assertThatThrownBy(() -> legendary.starUp(unique))
                .isInstanceOf(SynthesisFailedException.class);
    }

    @Test
    @DisplayName("무기가 다르면 예외 발생")
    void other_name_fail() {
        Item legendary1 = createItem(Weapon.of(SWORD, LEGENDARY), 0, 0, false);
        Item legendary2 = createItem(Weapon.of(AXE, LEGENDARY), 0, 0, false);

        assertThatThrownBy(() -> legendary1.starUp(legendary2))
                .isInstanceOf(SynthesisFailedException.class);
    }
}
