package com.idle.weapon.domain;

public enum Grade {

    NORMAL,
    RARE,
    EPIC,
    UNIQUE,
    LEGENDARY,
    END;

    public static Grade randomGrade(int random) {
        if (random >= 500 && random <= 599) {
            return Grade.NORMAL;
        } else if (random >= 790 && random <= 799) {
            return Grade.RARE;
        } else if (random >= 895 && random <= 899) {
            return Grade.EPIC;
        } else if (random == 999) {
            return Grade.UNIQUE;
        } else {
            return null;
        }
    }
}
