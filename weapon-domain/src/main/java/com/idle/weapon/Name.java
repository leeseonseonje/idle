package com.idle.weapon;

public enum Name {
    SWORD,
    SPEAR,
    AXE;

    public static Name randomGet(int random) {
        return values()[random];
    }
}
