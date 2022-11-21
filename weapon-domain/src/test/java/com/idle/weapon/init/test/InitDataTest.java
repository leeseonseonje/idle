package com.idle.weapon.init.test;

import com.idle.weapon.domain.Grade;
import com.idle.weapon.domain.Weapon;
import com.idle.weapon.repository.WeaponMemoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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
        assertThat(result.size()).isEqualTo(12);
        assertThat(result).extracting("name")
                .containsExactly("검", "검", "검", "검", "검", "검",
                        "도", "도", "도", "도", "도", "도");
        assertThat(result).extracting("grade")
                .containsExactly(NORMAL, RARE, EPIC, UNIQUE, LEGENDARY, END,
                        NORMAL, RARE, EPIC, UNIQUE, LEGENDARY, END);
    }
}
