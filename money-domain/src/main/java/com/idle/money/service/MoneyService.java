package com.idle.money.service;

import com.idle.money.domain.Money;
import com.idle.money.repository.MoneyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MoneyService {

    private final MoneyRepository moneyRepository;

        public void sprinkleMoney(List<Money> membersMoney) {
        for (Money money : membersMoney) {
            money.plusAmount(1000);
        }
    }
}
