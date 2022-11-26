package com.idle.shop.domain;

import com.idle.weapon.domain.Name;
import com.idle.weapon.domain.Grade;
import com.idle.weapon.domain.Weapon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.idle.weapon.domain.Name.*;
import static com.idle.weapon.domain.Grade.*;
import static org.assertj.core.api.Assertions.assertThat;

class RandomWeaponBoxTest {


    @Test
    @DisplayName("랜덤값 0이 나오면 검, 랜덤값 500 ~ 599사이의 값이 나오면 NORMAL")
    void random_weapon_box_normal() {
        Weapon weapon = getWeapon(0, 555);

        assertThat(weapon.getName()).isEqualTo(SWORD);
        assertThat(weapon.getGrade()).isEqualTo(NORMAL);
    }

    @Test
    @DisplayName("랜덤값 1이 나오면 창, 랜덤값 790 ~ 799사이의 값이 나오면 RARE")
    void random_weapon_box_rare() {
        Weapon weapon = getWeapon(1, 797);

        assertThat(weapon.getName()).isEqualTo(SPEAR);
        assertThat(weapon.getGrade()).isEqualTo(RARE);
    }

    @Test
    @DisplayName("랜덤값 2가 나오면 도끼, 랜덤값 899 ~ 895사이의 값이 나오면 EPIC")
    void random_weapon_box_epic() {
        Weapon weapon = getWeapon(2, 898);

        assertThat(weapon.getName()).isEqualTo(AXE);
        assertThat(weapon.getGrade()).isEqualTo(EPIC);
    }

    @Test
    @DisplayName("랜덤값 999가 나오면 UNIQUE")
    void random_weapon_box_unique() {
        Weapon weapon = getWeapon(0, 999);

        assertThat(weapon.getName()).isEqualTo(SWORD);
        assertThat(weapon.getGrade()).isEqualTo(UNIQUE);
    }

    @Test
    @DisplayName("위 값 외의 값이 나오면 null 반환")
    void random_weapon_box_boom() {
        Weapon weapon = getWeapon(0, 55);

        assertThat(weapon.getGrade()).isNull();
    }

    private Weapon getWeapon(int nameRandom, int gradeRandom) {
        Name name = get(nameRandom);
        Grade grade = randomGrade(gradeRandom);

        return Weapon.of(name, grade);
    }
}