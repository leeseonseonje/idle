package com.idle.money;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MoneyTest {

    @Test
    void plus_1000_money_amount() {
        Money sut = new Money();

        sut.plusAmount();

        assertThat(sut.getAmount()).isEqualTo(1000);
    }

    @Test
    void plus_amount_ten_repeat() {
        Money sut = new Money();

        for (int i = 0; i < 10; i++) {
            sut.plusAmount();
        }

        assertThat(sut.getAmount()).isEqualTo(10000);
    }
}
