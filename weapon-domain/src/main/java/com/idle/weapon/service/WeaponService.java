package com.idle.weapon.service;

import com.idle.weapon.repository.WeaponMemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class WeaponService {

    private final WeaponMemoryRepository weaponMemoryRepository;

    public void randomBox() {

    }
}
