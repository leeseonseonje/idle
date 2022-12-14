package com.idle.item.domain.test;

import com.idle.item.domain.Item;
import com.idle.item.exception.SynthesisFailedException;
import com.idle.weapon.domain.Weapon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.idle.item.domain.ItemFactory.*;
import static com.idle.weapon.domain.Grade.*;
import static com.idle.weapon.domain.Name.*;
import static org.assertj.core.api.Assertions.*;

public class ItemLegendaryUpTest {

    @Test
    @DisplayName("노말, 레어, 에픽, 유니크 각각 100씩 업그레이드 후 합성을 하면 레전더리 무기를 얻는다.")
    void item_synthesis() {
        Item normalWeapon = createItem(Weapon.of(SWORD, NORMAL), 100, 0, false);
        Item rareWeapon = createItem(Weapon.of(SWORD, RARE), 100, 0, false);
        Item epicWeapon = createItem(Weapon.of(SWORD, EPIC), 100, 0, false);
        Item uniqueWeapon = createItem(Weapon.of(SWORD, UNIQUE), 100, 0, false);
        List<Item> sut = List.of(normalWeapon, rareWeapon, epicWeapon, uniqueWeapon);

        Item item = sut.get(0).legendaryGradeUp(sut);

        assertThat(item.getWeapon().getName()).isEqualTo(SWORD);
        assertThat(item.getWeapon().getGrade()).isEqualTo(LEGENDARY);
    }

    @Test
    @DisplayName("같은 종류의 무기만 업그레이드 할 수 있다.")
    void item_synthesis_name_mismatch() {
        Item normalWeapon = createItem(Weapon.of(SWORD, NORMAL), 100, 0, false);
        Item rareWeapon = createItem(Weapon.of(SWORD, RARE), 100, 0, false);
        Item epicWeapon = createItem(Weapon.of(AXE, EPIC), 100, 0, false);
        Item uniqueWeapon = createItem(Weapon.of(SWORD, UNIQUE), 100, 0, false);
        List<Item> sut = List.of(normalWeapon, rareWeapon, epicWeapon, uniqueWeapon);

        assertThatThrownBy(() -> sut.get(0).legendaryGradeUp(sut)).isInstanceOf(SynthesisFailedException.class);
    }

    @Test
    @DisplayName("업그레이드 횟수는 100 이상이여야 한다.")
    void item_synthesis_upgrade_below_100() {
        Item normalWeapon = createItem(Weapon.of(SWORD, NORMAL), 100, 0, false);
        Item rareWeapon = createItem(Weapon.of(SWORD, RARE), 100, 0, false);
        Item epicWeapon = createItem(Weapon.of(SWORD, EPIC), 100, 0, false);
        Item uniqueWeapon = createItem(Weapon.of(SWORD, UNIQUE), 99, 0, false);
        List<Item> sut = List.of(normalWeapon, rareWeapon, epicWeapon, uniqueWeapon);

        assertThatThrownBy(() -> sut.get(0).legendaryGradeUp(sut)).isInstanceOf(SynthesisFailedException.class);
    }

    @Test
    @DisplayName("등급은 노말, 레어, 에픽, 유니크가 있어야 한다.")
    void item_synthesis_grade_lack() {
        Item normalWeapon = createItem(Weapon.of(SWORD, NORMAL), 100, 0, false);
        Item rareWeapon = createItem(Weapon.of(SWORD, NORMAL), 100, 0, false);
        Item epicWeapon = createItem(Weapon.of(SWORD, EPIC), 100, 0, false);
        Item uniqueWeapon = createItem(Weapon.of(SWORD, UNIQUE), 100, 0, false);
        List<Item> sut = List.of(normalWeapon, rareWeapon, epicWeapon, uniqueWeapon);

        assertThatThrownBy(() -> sut.get(0).legendaryGradeUp(sut)).isInstanceOf(SynthesisFailedException.class);
    }
}
