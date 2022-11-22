package com.idle.shop.domain;

import com.idle.shop.domain.product.Product;
import com.idle.weapon.domain.Weapon;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class ShopTest {

    @Test
    void random_int_0_to_999() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < 100; i++) {
            System.out.println(random.nextInt(1000));
        }
    }

    @Test
    void random_weapon_box_logic() {
        Weapon weapon = Product.RANDOM_WEAPON_BOX.getExecute().get(Weapon.type());
        System.out.println(weapon.getName());
        System.out.println(weapon.getGrade());
    }
}