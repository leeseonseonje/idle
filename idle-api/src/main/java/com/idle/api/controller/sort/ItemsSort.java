package com.idle.api.controller.sort;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ItemsSort {

    GRADE("weapon.grade, star, upgrade"),
    NAME("weapon.name, weapon.grade, star, upgrade");

    private String order;

    public String[] condition() {
        return this.order.split(", ");
    }
}
