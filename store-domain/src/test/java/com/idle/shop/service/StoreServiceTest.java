package com.idle.shop.service;

import com.idle.money.domain.Money;
import com.idle.random.MockRandomGenerator;
import com.idle.random.RandomGenerator;
import com.idle.weapon.domain.Grade;
import com.idle.weapon.domain.Name;
import com.idle.weapon.domain.Weapon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static com.idle.shop.domain.weapon.Product.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class StoreServiceTest {

    @Autowired
    StoreService sut;

    @TestConfiguration
    static class MockConfig {

        @Bean
        public RandomGenerator randomGenerator() {
            return new MockRandomGenerator(0);
        }
    }

    @Test
    @DisplayName("랜덤으로 무기를 받는다.")
    void random_weapon() {
        Money money = new Money();
        money.amountIncrease(1000);
        Weapon weapon = sut.weaponPurchase(money, RANDOM_WEAPON_BOX);

        assertThat(weapon.getName()).isEqualTo(Name.SWORD);
        assertThat(weapon.getGrade()).isEqualTo(Grade.NORMAL);
    }
}