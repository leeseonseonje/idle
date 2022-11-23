package com.idle.shop.service;

import com.idle.money.domain.Money;
import com.idle.weapon.domain.Weapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void receive_random_weapon() {
        ShopService sut = new ShopService();
        Money money = new Money();
        money.plusAmount(1000);

        Weapon weapon = sut.randomWeaponBox(money);


    }
}