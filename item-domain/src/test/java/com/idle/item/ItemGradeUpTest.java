package com.idle.item;

import com.idle.item.domain.Item;
import com.idle.money.domain.Money;
import com.idle.weapon.domain.Weapon;
import org.junit.jupiter.api.Test;

import static com.idle.item.domain.ItemFactory.*;
import static com.idle.weapon.domain.Grade.*;
import static com.idle.weapon.domain.Name.*;
import static org.assertj.core.api.Assertions.*;

public class ItemGradeUpTest {

    @Test
    void normal_to_rare() {
        Item item = createItem(Weapon.of(SWORD, NORMAL), 0, 0, false);
        Money money = createMoney(1000);
        item.gradeUp(money, 990);

        assertThat(item.getWeapon().getGrade()).isEqualTo(RARE);
    }

    @Test
    void rare_to_epic() {
        Item item = createItem(Weapon.of(SWORD, RARE), 0, 0, false);
        Money money = createMoney(1000);
        item.gradeUp(money, 995);

        assertThat(item.getWeapon().getGrade()).isEqualTo(EPIC);
    }

    @Test
    void epic_to_unique() {
        Item item = createItem(Weapon.of(SWORD, EPIC), 0, 0, false);
        Money money = createMoney(1000);
        item.gradeUp(money, 999);

        assertThat(item.getWeapon().getGrade()).isEqualTo(UNIQUE);
    }
}
