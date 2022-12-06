package com.idle.money;

import com.idle.money.domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.*;

public class MoneyTest {

    @Test
    @DisplayName("money의 amount가 인자로 넘긴 int값 만큼 증가한다.")
    void plus_1000_money_amount() {
        Money sut = Money.of(0, LocalDateTime.now());

        sut.amountIncrease(1000);

        assertThat(sut.getAmount()).isEqualTo(1000);
    }

    @Test
    @DisplayName("money의 amount가 인자로 넘긴 int값 만큼 증가한다.")
    void plus_amount_ten_repeat() {
        Money sut = Money.of(0, LocalDateTime.now());

        for (int i = 0; i < 10; i++) {
            sut.amountIncrease(1000);
        }

        assertThat(sut.getAmount()).isEqualTo(10000);
    }

    @Test
    void test() {
        LocalDateTime of = LocalDateTime.of(2022, 12, 6, 10, 20, 0);
        LocalDateTime of1 = LocalDateTime.of(2022, 12, 6, 10, 50, 45);
        long between = ChronoUnit.SECONDS.between(of, of1);
        System.out.println(between);
        System.out.println(between / 60);
        System.out.println(between % 60);
        LocalDateTime localDateTime = of1.minusSeconds(between);
        System.out.println(localDateTime);

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        LocalDateTime localDateTime1 = now.minusSeconds(60);
        System.out.println(localDateTime1);
    }
}
