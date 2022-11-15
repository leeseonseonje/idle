package com.idle.money.service;

import com.idle.member.Member;
import com.idle.member.repository.MemberRepository;
import com.idle.money.Money;
import com.idle.money.repository.MoneyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class MoneyServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MoneyRepository moneyRepository;

    MoneyService sut;

    @BeforeEach
    void init() {
        sut = new MoneyService(moneyRepository);
        Member member = Member.of("member@eamil.com");
        Money money = Money.of(member);
        memberRepository.save(member);
        moneyRepository.save(money);
    }

    @Test
    void plus_amount_db_update() {

        sut.idlePlusMoney(1L);

        Money money = moneyRepository.findById(2L).get();
        assertThat(money.getAmount()).isEqualTo(1000);
    }
}