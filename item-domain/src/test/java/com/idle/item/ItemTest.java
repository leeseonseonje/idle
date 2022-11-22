package com.idle.item;

import com.idle.member.Member;
import com.idle.money.domain.Money;
import com.idle.money.exception.ShortOfMoneyException;
import com.idle.weapon.Name;
import com.idle.weapon.domain.Grade;
import com.idle.weapon.domain.Weapon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ItemTest {

    private Item sut;
    private Money money;

    @Test
    @DisplayName("아이템을 업그레이드 하면 돈이 차감되고, 무기가 1씩 업그레이드 된다.")
    void item_upgrade_plus() {
        createItem();
        createMoney(10000);

        sut.upgrade(money);

        assertThat(sut.getUpgrade()).isEqualTo(1);
        assertThat(money.getAmount()).isEqualTo(99000);
    }

    @Test
    @DisplayName("아이템의 업그레이드 횟수만큼 업그레이드에 드는 비용이 증가한다.")
    void item_upgrade_plus_overlap() {
        createItem();
        createMoney(100000);

        for (int i = 0; i < 10; i++) {
            sut.upgrade(money);
        }

        assertThat(sut.getUpgrade()).isEqualTo(10);
        assertThat(money.getAmount()).isEqualTo(89955);
    }

    @Test
    @DisplayName("돈이 부족하면 ShortOfMoneyException 발생")
    void item_upgrade_short_of_money() {
        createItem();
        createMoney(100);

        assertThatThrownBy(() -> sut.upgrade(money)).isInstanceOf(ShortOfMoneyException.class);
    }

    private void createItem() {
        sut = Item.of(Member.of("email"), Weapon.of(Name.SWORD, Grade.NORMAL));
    }

    private void createMoney(int amount) {
        money = new Money();
        money.plusAmount(amount);
    }
}