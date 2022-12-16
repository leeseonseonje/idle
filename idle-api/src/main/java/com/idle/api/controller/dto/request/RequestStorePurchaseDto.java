package com.idle.api.controller.dto.request;

import com.idle.shop.domain.weapon.Product;

public record RequestStorePurchaseDto(
        Long memberId,
        Product product
) {
}
