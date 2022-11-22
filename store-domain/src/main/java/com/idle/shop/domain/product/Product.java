package com.idle.shop.domain.product;

import com.idle.weapon.domain.Weapon;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Product {

    RANDOM_WEAPON_BOX(new Execute() {
        @Override
        public <T> T get(T type) {
            return (T) new RandomWeaponBox().randomWeapon();
        }
    });

    private Execute execute;
}