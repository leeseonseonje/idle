package com.idle.item;

import com.idle.item.domain.Item;
import com.idle.member.Member;
import com.idle.money.domain.Money;
import com.idle.weapon.domain.Weapon;

public class ItemFactory {

    public static Item createItem(Weapon weapon) {
        return Item.of(Member.of("email"), weapon);
    }

    public static Money createMoney(int amount) {
        Money money = new Money();
        money.plusAmount(amount);
        return money;
    }
}
