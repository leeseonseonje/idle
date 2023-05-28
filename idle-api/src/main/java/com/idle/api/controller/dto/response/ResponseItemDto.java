package com.idle.api.controller.dto.response;

import com.idle.item.domain.Item;
import com.idle.weapon.domain.Weapon;

public record
ResponseItemDto(
        Long itemId,
        Weapon weapon,
        int upgrade,
        int star,
        boolean isWear

) {

    public static ResponseItemDto createDto(Item item) {
        return new ResponseItemDto(item.getId(), item.getWeapon(), item.getUpgrade(), item.getStar(), item.isWear());
    }
}
