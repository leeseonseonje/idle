package com.idle.shop.domain;

import com.idle.money.domain.Money;
import com.idle.random.MockRandomGenerator;
import com.idle.shop.domain.weapon.RandomWeaponBox;
import com.idle.shop.domain.weapon.WeaponStore;
import com.idle.weapon.domain.Weapon;
import org.junit.jupiter.api.Test;

import static com.idle.weapon.domain.Grade.*;
import static com.idle.weapon.domain.Name.*;
import static org.assertj.core.api.Assertions.*;

class RandomWeaponBoxTest {

    @Test
    void random_weapon() {
        WeaponStore sut = new RandomWeaponBox(new MockRandomGenerator(0));
        Money money = new Money();
        money.plusAmount(1000);

        Weapon weapon = sut.purchase(money, 1000);

        assertThat(weapon.getName()).isEqualTo(SWORD);
        assertThat(weapon.getGrade()).isEqualTo(NORMAL);
    }
}