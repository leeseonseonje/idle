package com.idle.weapon.repository;

import com.idle.weapon.domain.Weapon;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class WeaponMemoryRepository {

    private static final Map<Long, Weapon> weapons = new HashMap<>();

    public void saveAll(List<Weapon> findWeapons) {
        Map<Long, Weapon> findWeaponsToMap = findWeapons
                .stream()
                .collect(Collectors.toMap(Weapon::getId, Function.identity()));
        weapons.putAll(findWeaponsToMap);
    }

    public Weapon findById(Long weaponId) {
        return weapons.get(weaponId);
    }

    public List<Weapon> findAll() {
        return new ArrayList<>(weapons.values());
    }
}
