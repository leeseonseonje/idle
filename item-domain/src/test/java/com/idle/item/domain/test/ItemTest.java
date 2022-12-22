package com.idle.item.domain.test;

import com.idle.item.domain.Item;
import com.idle.item.domain.ItemFactory;
import com.idle.weapon.domain.Grade;
import com.idle.weapon.domain.Name;
import com.idle.weapon.domain.Weapon;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ItemTest {

    @Test
    void item_change() {
        Item currentItem = ItemFactory.createItem(Weapon.of(Name.SWORD, Grade.END), 1000, 0, true);
        Item newWearItem = ItemFactory.createItem(Weapon.of(Name.SPEAR, Grade.END), 1000, 0, false);

        newWearItem.itemWear(currentItem);

        assertThat(currentItem.isWear()).isFalse();
        assertThat(newWearItem.isWear()).isTrue();
    }
}
