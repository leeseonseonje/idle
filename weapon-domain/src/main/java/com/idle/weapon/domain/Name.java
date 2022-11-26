package com.idle.weapon.domain;

public enum Name {
    SWORD,
    SPEAR,
    AXE;

    public static Name get(int random) {
        return values()[random];
    }
}
