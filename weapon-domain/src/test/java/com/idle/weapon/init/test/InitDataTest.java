package com.idle.weapon.init.test;

import com.idle.weapon.domain.Weapon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.idle.weapon.domain.Grade.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class InitDataTest {

    @Autowired
    WeaponMemoryRepository weaponMemoryRepository;

    @Test
    void initTest() {
        List<Weapon> result = weaponMemoryRepository.findAll();
        assertThat(result.size()).isEqualTo(18);
        assertThat(result).extracting("grade")
                .containsExactly(NORMAL, RARE, EPIC, UNIQUE, LEGENDARY, END,
                        NORMAL, RARE, EPIC, UNIQUE, LEGENDARY, END,
                        NORMAL, RARE, EPIC, UNIQUE, LEGENDARY, END);
    }
}
