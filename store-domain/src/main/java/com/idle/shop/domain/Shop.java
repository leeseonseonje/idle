package com.idle.shop.domain;

import com.idle.money.domain.Money;
import com.idle.shop.domain.product.Product;
import lombok.*;

@Getter
public class Shop {

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

    public <T> T purchase(Money money, T type) {
        money.payment(this.price);
        return product.getExecute().get(type);
    }
}
