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

public class ItemSynthesisTest {

    @Test
    @DisplayName("노말, 레어, 에픽, 유니크 각각 100씩 업그레이드 후 합성을 하면 레전더리 무기를 얻는다.")
    void item_synthesis() {
        Item normalWeapon = createItem(Weapon.of(SWORD, NORMAL));
        Item rareWeapon = createItem(Weapon.of(SWORD, RARE));
        Item epicWeapon = createItem(Weapon.of(SWORD, EPIC));
        Item uniqueWeapon = createItem(Weapon.of(SWORD, UNIQUE));
        List<Item> sut = List.of(normalWeapon, rareWeapon, epicWeapon, uniqueWeapon);
        for (Item item : sut) {
            for (int i = 0; i < 100; i++) {
                item.upgrade(createMoney(99999));
            }
        }

        Weapon weapon = sut.get(0).synthesis(sut);

        assertThat(weapon.getName()).isEqualTo(SWORD);
        assertThat(weapon.getGrade()).isEqualTo(LEGENDARY);
    }

    @Test
    @DisplayName("같은 종류의 무기만 업그레이드 할 수 있다.")
    void item_synthesis_name_mismatch() {
        Item normalWeapon = createItem(Weapon.of(SWORD, NORMAL));
        Item rareWeapon = createItem(Weapon.of(SWORD, RARE));
        Item epicWeapon = createItem(Weapon.of(AXE, EPIC));
        Item uniqueWeapon = createItem(Weapon.of(SWORD, UNIQUE));
        List<Item> sut = List.of(normalWeapon, rareWeapon, epicWeapon, uniqueWeapon);
        for (Item item : sut) {
            for (int i = 0; i < 100; i++) {
                item.upgrade(createMoney(99999));
            }
        }

        assertThatThrownBy(() -> sut.get(0).synthesis(sut)).isInstanceOf(SynthesisFailedException.class);
    }

    @Test
    @DisplayName("업그레이드 횟수는 100 이상이여야 한다.")
    void item_synthesis_upgrade_below_100() {
        Item normalWeapon = createItem(Weapon.of(SWORD, NORMAL));
        Item rareWeapon = createItem(Weapon.of(SWORD, RARE));
        Item epicWeapon = createItem(Weapon.of(SWORD, EPIC));
        Item uniqueWeapon = createItem(Weapon.of(SWORD, UNIQUE));
        List<Item> sut = List.of(normalWeapon, rareWeapon, epicWeapon, uniqueWeapon);
        for (Item item : sut) {
            for (int i = 0; i < 99; i++) {
                item.upgrade(createMoney(99999));
            }
        }

        assertThatThrownBy(() -> sut.get(0).synthesis(sut)).isInstanceOf(SynthesisFailedException.class);
    }

    @Test
    @DisplayName("등급은 노말, 레어, 에픽, 유니크가 있어야 한다.")
    void item_synthesis_grade_lack() {
        Item normalWeapon = createItem(Weapon.of(SWORD, NORMAL));
        Item rareWeapon = createItem(Weapon.of(SWORD, NORMAL));
        Item epicWeapon = createItem(Weapon.of(SWORD, EPIC));
        Item uniqueWeapon = createItem(Weapon.of(SWORD, UNIQUE));
        List<Item> sut = List.of(normalWeapon, rareWeapon, epicWeapon, uniqueWeapon);
        for (Item item : sut) {
            for (int i = 0; i < 100; i++) {
                item.upgrade(createMoney(99999));
            }
        }

        assertThatThrownBy(() -> sut.get(0).synthesis(sut)).isInstanceOf(SynthesisFailedException.class);
    }
}
