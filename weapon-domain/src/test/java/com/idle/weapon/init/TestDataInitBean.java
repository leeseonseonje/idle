package com.idle.weapon.init;

import com.idle.weapon.repository.WeaponMemoryRepository;
import com.idle.weapon.repository.WeaponRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestDataInitBean {

    @Bean
    public TestDataInit testDataInit(WeaponRepository weaponRepository, WeaponMemoryRepository weaponMemoryRepository) {
        return new TestDataInit(weaponRepository, weaponMemoryRepository);
    }
}