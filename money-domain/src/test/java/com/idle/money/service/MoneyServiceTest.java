package com.idle.money.service;

import com.idle.money.Money;
import com.idle.money.repository.MoneyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class MoneyServiceTest {


    @Autowired
    MoneyRepository moneyRepository;

    MoneyService sut;

    @Test
    void plus_amount_db_update() {
        sut = new MoneyService(moneyRepository);
        List<Money> membersMoney = createMembersMoney();

        sut.sprinkleMoney(membersMoney);

        List<Money> result = moneyRepository.findAll();
        assertThat(result)
                .extracting("amount")
                .containsExactly(1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000);
    }

    private List<Money> createMembersMoney() {
        List<Money> membersMoney = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Money money = new Money();
            moneyRepository.save(money);
            membersMoney.add(money);
        }
        return membersMoney;
    }
}