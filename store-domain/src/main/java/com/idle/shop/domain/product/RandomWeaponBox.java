package com.idle.shop.domain.product;

import com.idle.shop.exception.BoomException;
import com.idle.weapon.Name;
import com.idle.weapon.domain.Grade;
import com.idle.weapon.domain.Weapon;

import java.util.concurrent.ThreadLocalRandom;

public class RandomWeaponBox {

    public Weapon randomWeapon() {
        Name name = getWeaponName(Name.values().length);

        Grade grade = randomGrade();

        return Weapon.of(name, grade);
    }

    private Grade randomGrade() {
        int number = randomNumber(1000);

        if (number >= 500 && number <= 599) {
            return Grade.NORMAL;
        } else if (number >= 790 && number <= 799) {
            return Grade.RARE;
        } else if (number >= 895 && number <= 899) {
            return Grade.EPIC;
        } else if (number == 999) {
            return Grade.UNIQUE;
        } else {
            throw new BoomException("꽝");
        }
    }

    private Name getWeaponName(int bound) {
        int number = randomNumber(bound);
        return Name.randomGet(number);
    }

    private int randomNumber(int bound) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextInt(bound);
    }
}
