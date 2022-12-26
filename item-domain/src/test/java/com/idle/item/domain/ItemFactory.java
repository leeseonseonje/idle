package com.idle.item.domain;

import com.idle.member.Member;
import com.idle.money.domain.Money;
import com.idle.weapon.domain.Weapon;

import java.time.LocalDateTime;

public class ItemFactory {

    public static Item createItem(Weapon weapon, int upgrade, int star, boolean isWear) {
        return Item.of(Member.simple("email"), weapon, upgrade, star, isWear);
    }

    public static Money createMoney(int amount) {
        return Money.of(amount, LocalDateTime.now());
    }
}
