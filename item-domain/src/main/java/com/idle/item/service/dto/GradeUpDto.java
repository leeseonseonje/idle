package com.idle.item.service.dto;

import com.idle.item.domain.Item;
import com.idle.weapon.domain.Weapon;

public record GradeUpDto(
        Long itemId,
        Weapon weapon,
        int upgrade
) {

    public static GradeUpDto createDto(Item item) {
        return new GradeUpDto(item.getId(), item.getWeapon(), item.getUpgrade());
    }
}
