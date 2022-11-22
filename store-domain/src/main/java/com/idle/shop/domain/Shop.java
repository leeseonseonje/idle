package com.idle.shop.domain;

import com.idle.money.domain.Money;
import com.idle.shop.domain.product.Product;
import com.idle.weapon.domain.Weapon;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.function.Supplier;

import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Shop {

    @Id
    @GeneratedValue
    private Long id;

    private Product product;

    private int price;

    @Builder
    public Shop(Product product, int price) {
        this.product = product;
        this.price = price;
    }

    public static Shop of(Product product, int price) {
        return Shop.builder()
                .product(product)
                .price(price)
                .build();
    }

    public <T> T purchase(Money money, int price, T type) {
        money.payment(price);
        return product.getExecute().get(type);
    }
}
