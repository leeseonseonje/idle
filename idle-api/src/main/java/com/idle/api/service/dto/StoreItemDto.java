package com.idle.api.service.dto;

import com.idle.weapon.domain.Weapon;

public record StoreItemDto(
        Long itemId,
        Weapon weapon
) {
}
