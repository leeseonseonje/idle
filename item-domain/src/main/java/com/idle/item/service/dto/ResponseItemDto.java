package com.idle.item.service.dto;

import com.idle.item.domain.Item;
import com.idle.weapon.domain.Weapon;

public record ResponseItemDto(
        Long itemId,
        Weapon weapon,
        int upgrade,
        int star
) {

    public static ResponseItemDto toDto(Item item) {
        return new ResponseItemDto(item.getId(), item.getWeapon(), item.getUpgrade(), item.getStar());
    }
}
