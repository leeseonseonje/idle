package com.idle.shop.domain.weapon;

import com.idle.money.domain.Money;
import com.idle.weapon.domain.Weapon;

public abstract class WeaponStore {

    protected abstract Weapon getWeapon();

    public Weapon purchase(Money money, int price) {
        money.payment(price);
        return getWeapon();
    }
}
