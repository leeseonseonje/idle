package com.idle.shop.domain.weapon;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Locale;

@Getter
@AllArgsConstructor
public enum Product {

    RANDOM_WEAPON_BOX("randomWeaponBox", 1000),
    ;

    private String name;

    private int price;

    @JsonCreator
    public static Product from(String name) {
        try {
            return Product.valueOf(name.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("존재하지 않는 상품입니다.");
        }
    }
}
