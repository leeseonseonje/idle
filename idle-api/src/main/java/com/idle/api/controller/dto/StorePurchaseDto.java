package com.idle.api.controller.dto;

import com.idle.shop.domain.weapon.Product;

public record StorePurchaseDto(
        Long memberId,
        Product product
) {
}
