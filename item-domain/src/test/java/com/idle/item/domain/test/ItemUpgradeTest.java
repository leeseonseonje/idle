package com.idle.item.domain.test;

import com.idle.item.domain.Item;
import com.idle.item.domain.ItemFactory;
import com.idle.money.domain.Money;
import com.idle.money.exception.ShortOfMoneyException;
import com.idle.weapon.domain.Weapon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.idle.weapon.domain.Grade.*;
import static com.idle.weapon.domain.Name.*;
import static org.assertj.core.api.Assertions.*;

class ItemUpgradeTest {

    @Test
    @DisplayName("아이템을 업그레이드 하면 돈이 차감되고, 무기가 1씩 업그레이드 된다.")
    void item_upgrade_plus() {
        Item sut = ItemFactory.createItem(Weapon.of(SWORD, NORMAL));
        Money money = ItemFactory.createMoney(100000);
        sut.upgrade(money);

        assertThat(sut.getUpgrade()).isEqualTo(1);
        assertThat(money.getAmount()).isEqualTo(99000);
    }

    @Test
    @DisplayName("아이템의 업그레이드 횟수만큼 업그레이드에 드는 비용이 증가한다.")
    void item_upgrade_plus_overlap() {
        Item sut = ItemFactory.createItem(Weapon.of(SWORD, NORMAL));
        Money money = ItemFactory.createMoney(100000);
        for (int i = 0; i < 10; i++) {
            sut.upgrade(money);
        }

        assertThat(sut.getUpgrade()).isEqualTo(10);
        assertThat(money.getAmount()).isEqualTo(89955);
    }

    @Test
    @DisplayName("돈이 부족하면 ShortOfMoneyException 발생")
    void item_upgrade_short_of_money() {
        Item sut = ItemFactory.createItem(Weapon.of(SWORD, NORMAL));
        Money money = ItemFactory.createMoney(100);
        assertThatThrownBy(() -> sut.upgrade(money)).isInstanceOf(ShortOfMoneyException.class);
    }
}