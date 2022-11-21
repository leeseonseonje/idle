package com.idle.store.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
}
