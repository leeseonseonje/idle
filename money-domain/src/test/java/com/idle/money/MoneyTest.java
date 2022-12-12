package com.idle.money;

import com.idle.money.domain.Money;
import com.idle.money.service.MoneyService;
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
    @DisplayName("마지막 수금 시간과 현재 시간을 기준으로 1분당 1000원씩 증가")
    void last_collect_money_time_now_time_between_increase() {
        Money sut = Money.of(0, LocalDateTime.of(2020, 12, 1, 10, 0, 30));

        sut.perMinutePutMoney(LocalDateTime.of(2020, 12, 1, 11, 0, 50));
        assertThat(sut.getAmount()).isEqualTo(60000);
        assertThat(sut.getLastCollectMoneyTime()).isEqualTo("2020-12-01T11:00:30");
    }

    @Test
    @DisplayName("남는 초 계산")
    void remaining_seconds() {
        Money sut = Money.of(0, LocalDateTime.of(2020, 12, 1, 10, 0, 30));

        sut.perMinutePutMoney(LocalDateTime.of(2020, 12, 1, 10, 0, 50));

        sut.perMinutePutMoney(LocalDateTime.of(2020, 12, 1, 10, 1, 30));

        assertThat(sut.getAmount()).isEqualTo(1000);
        assertThat(sut.getLastCollectMoneyTime()).isEqualTo("2020-12-01T10:01:30");
    }
}
