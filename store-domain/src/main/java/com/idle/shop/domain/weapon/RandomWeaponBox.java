package com.idle.shop.domain.weapon;

import com.idle.shop.exception.BoomException;
import com.idle.weapon.domain.Grade;
import com.idle.weapon.domain.Name;
import com.idle.weapon.domain.Weapon;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class RandomWeaponBox extends WeaponStore {

    @Override
    public Weapon getWeapon() {
        return randomWeapon();
    }

    private Weapon randomWeapon() {
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
        return Name.get(number);
    }

    private int randomNumber(int bound) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextInt(bound);
    }
}
