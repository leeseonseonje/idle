package com.idle.money.service;

import com.idle.money.domain.Money;
import com.idle.money.repository.MoneyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MoneyServiceTest {

    MoneyService sut;

    @Test
    @DisplayName("마지막 수금 시간과 현재 시간을 기준으로 1분당 1000원씩 증가")
    void last_collect_money_time_now_time_between_increase() {
        Money money = Money.of(0, LocalDateTime.of(2020, 12, 1, 10, 0, 30));
        sut = new MoneyService();

        sut.putMoney(money, LocalDateTime.of(2020, 12, 1, 11, 0, 50));
        assertThat(money.getAmount()).isEqualTo(60000);
        assertThat(money.getLastCollectMoneyTime()).isEqualTo("2020-12-01T11:00:30");
    }

    @Test
    @DisplayName("남는 초 계산")
    void remaining_seconds() {
        Money money = Money.of(0, LocalDateTime.of(2020, 12, 1, 10, 0, 30));
        sut = new MoneyService();
        sut.putMoney(money, LocalDateTime.of(2020, 12, 1, 10, 0, 50));

        sut.putMoney(money, LocalDateTime.of(2020, 12, 1, 10, 1, 30));

        assertThat(money.getAmount()).isEqualTo(1000);
        assertThat(money.getLastCollectMoneyTime()).isEqualTo("2020-12-01T10:01:30");
    }
}