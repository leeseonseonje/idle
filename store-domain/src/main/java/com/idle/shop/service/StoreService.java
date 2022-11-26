package com.idle.shop.service;

import com.idle.money.domain.Money;
import com.idle.shop.domain.weapon.Product;
import com.idle.shop.domain.weapon.WeaponStore;
import com.idle.weapon.domain.Weapon;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final ApplicationContext applicationContext;

    public Weapon weaponPurchase(Money money, Product product) {
        WeaponStore store = applicationContext.getBean(product.getName(), WeaponStore.class);
        return store.purchase(money, product.getPrice());
    }
}
