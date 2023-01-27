package com.idle.api.controller.dto.response;

import com.idle.shop.domain.weapon.Product;

public record ResponseStoreDto(
        String name,
        int price
) {

    public static ResponseStoreDto createDto(Product product) {
        return new ResponseStoreDto(product.getName(), product.getPrice());
    }
}
