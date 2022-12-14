package com.idle.money.service;

import com.idle.money.domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class MoneyServiceTest {

    MoneyService sut;

    @Test
    @DisplayName("마지막 수금 시간과 현재 시간을 기준으로 1분당 1000원씩 증가")
    void last_collect_money_time_now_time_between_increase() {
        Money money = Money.of(0, LocalDateTime.of(2020, 12, 1, 10, 0, 30));
        sut = new MoneyService();

        int result = sut.perMinutePutMoney(money,
                LocalDateTime.of(2020, 12, 1, 11, 0, 50));
        assertThat(result).isEqualTo(60000);
        assertThat(money.getLastCollectMoneyTime()).isEqualTo("2020-12-01T11:00:30");
    }
}