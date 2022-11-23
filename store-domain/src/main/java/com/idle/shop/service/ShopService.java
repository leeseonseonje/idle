package com.idle.shop.service;

import com.idle.money.domain.Money;
import com.idle.shop.domain.Shop;
import com.idle.shop.domain.product.Product;
import com.idle.weapon.domain.Weapon;
import org.springframework.stereotype.Service;

import static com.idle.shop.domain.product.Product.*;

@Service
public class ShopService {

    public Weapon randomWeaponBox(Money money) {
        return purchase(money, RANDOM_WEAPON_BOX, 1000, Weapon.type());
    }

    public <T> T purchase(Money money, Product product, int price, T type) {
        Shop shop = Shop.of(product, price);
        return shop.purchase(money, type);
    }

}
