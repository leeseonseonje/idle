package com.idle.shop.domain.weapon;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Product {

    RANDOM_WEAPON_BOX("randomWeaponBox", 1000);

    private String name;

    private int price;
}
