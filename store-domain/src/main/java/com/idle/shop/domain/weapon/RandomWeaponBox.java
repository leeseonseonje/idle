package com.idle.shop.domain.weapon;

import com.idle.random.RandomGenerator;
import com.idle.weapon.domain.Name;
import com.idle.weapon.domain.Weapon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RandomWeaponBox extends WeaponStore {

    private final RandomGenerator randomGenerator;

    @Override
    public Weapon getWeapon() {
        int random = randomGenerator.generate(Name.values().length);
        return Weapon.randomWeapon(random);
    }
}
