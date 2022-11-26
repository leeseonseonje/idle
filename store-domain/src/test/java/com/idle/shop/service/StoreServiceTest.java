package com.idle.shop.service;

import com.idle.money.domain.Money;
import com.idle.money.exception.ShortOfMoneyException;
import com.idle.weapon.domain.Weapon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.idle.shop.domain.weapon.Product.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class StoreServiceTest {

    @Autowired
    StoreService sut;

    @Test
    @DisplayName("랜덤으로 무기를 받는다.")
    void random_weapon() {
        Money money = new Money();
        money.plusAmount(1000);
        Weapon weapon = sut.weaponPurchase(money, RANDOM_WEAPON_BOX);
    }

    @Test
    @DisplayName("돈이 부족하면 ShortOfMoneyException")
    void payment_short_of_money_exception() {
        Money money = new Money();
        money.plusAmount(999);

        assertThatThrownBy(() -> sut.weaponPurchase(money, RANDOM_WEAPON_BOX))
                .isInstanceOf(ShortOfMoneyException.class);
    }
}