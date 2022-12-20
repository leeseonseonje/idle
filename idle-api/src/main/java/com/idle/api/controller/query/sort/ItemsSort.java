package com.idle.api.controller.query.sort;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ItemsSort {

    GRADE("weapon.grade, star, upgrade"),
    NAME("weapon.name, weapon.grade, star, upgrade");

    private String order;

    public String[] conditions() {
        return this.order.split(", ");
    }
}
