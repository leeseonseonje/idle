package com.idle.weapon.init;

import com.idle.weapon.NAME;
import com.idle.weapon.domain.Grade;
import com.idle.weapon.domain.Weapon;
import com.idle.weapon.repository.WeaponMemoryRepository;
import com.idle.weapon.repository.WeaponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import static com.idle.weapon.domain.Grade.values;

@RequiredArgsConstructor
public class TestDataInit {

    private final WeaponRepository weaponRepository;
    private final WeaponMemoryRepository weaponMemoryRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initTestData() {
        initDbData();
        weaponMemoryRepository.saveAll(weaponRepository.findAll());
    }

    private void initDbData() {
        for (Grade grade : values()) {
            weaponRepository.save(Weapon.of(NAME.SWORD, grade));
        }
        for (Grade grade : values()) {
            weaponRepository.save(Weapon.of(NAME.SPEAR, grade));
        }
        for (Grade grade : values()) {
            weaponRepository.save(Weapon.of(NAME.AXE, grade));
        }
    }
}
