package com.idle.shop.domain.weapon;

import com.idle.money.domain.Money;
import com.idle.weapon.domain.Weapon;

public abstract class WeaponStore {

    protected abstract Weapon getWeapon();

    public Weapon purchase(Money money, int amount) {
        money.payment(amount);
        return getWeapon();
    }
}
