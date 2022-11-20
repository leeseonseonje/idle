package com.idle.money;

import com.idle.money.domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MoneyTest {

    @Test
    @DisplayName("money의 amount가 인자로 넘긴 int값 만큼 증가한다.")
    void plus_1000_money_amount() {
        Money sut = new Money();

        sut.plusAmount(1000);

        assertThat(sut.getAmount()).isEqualTo(1000);
    }

    @Test
    @DisplayName("money의 amount가 인자로 넘긴 int값 만큼 증가한다.")
    void plus_amount_ten_repeat() {
        Money sut = new Money();

        for (int i = 0; i < 10; i++) {
            sut.plusAmount(1000);
        }

        assertThat(sut.getAmount()).isEqualTo(10000);
    }
}
