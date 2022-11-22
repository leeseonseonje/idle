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
        int random = randomNumber(1000);

        Grade grade = Grade.randomGrade(random);
        if (grade != null) {
            return grade;
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
