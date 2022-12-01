package com.idle.item.domain.test;

import com.idle.item.domain.Item;
import com.idle.money.domain.Money;
import com.idle.money.exception.ShortOfMoneyException;
import com.idle.weapon.domain.Weapon;
import com.idle.weapon.exception.GradeUpFailedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.idle.item.domain.ItemFactory.*;
import static com.idle.weapon.domain.Grade.*;
import static com.idle.weapon.domain.Name.*;
import static org.assertj.core.api.Assertions.*;

public class ItemEndTest {

    @Test
    @DisplayName("레전더리 무기 10성 이상 달성 하게되면 END등급 승급 가능")
    void legendary_start_ten_end() {
        Item item = createItem(Weapon.of(SWORD, LEGENDARY));
        Money money = createMoney(1000000);
        for (int i = 0; i < 10; i++) {
            item.legendarySynthesis(createItem(Weapon.of(SWORD, LEGENDARY)));
        }

        item.end(money);

        assertThat(item.getWeapon().getGrade()).isEqualTo(END);
        assertThat(money.getAmount()).isEqualTo(0);
    }

    @Test
    @DisplayName("돈이 100만원이 필요하다.")
    void one_hundred_needed() {
        Item item = createItem(Weapon.of(SWORD, LEGENDARY));
        Money money = createMoney(999999);
        for (int i = 0; i < 10; i++) {
            item.legendarySynthesis(createItem(Weapon.of(SWORD, LEGENDARY)));
        }

        assertThatThrownBy(() -> item.end(money))
                .isInstanceOf(ShortOfMoneyException.class);
    }

    @Test
    @DisplayName("별이 10개 이상이어야 한다.")
    void ten_stars_required() {
        Item item = createItem(Weapon.of(SWORD, LEGENDARY));
        Money money = createMoney(1000000);
        for (int i = 0; i < 9; i++) {
            item.legendarySynthesis(createItem(Weapon.of(SWORD, LEGENDARY)));
        }

        assertThatThrownBy(() -> item.end(money))
                .isInstanceOf(GradeUpFailedException.class);
    }
}
