package com.idle.money.service;

import com.idle.money.domain.Money;
import com.idle.money.repository.MoneyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class MoneyService {

    public void perMinutePutMoney(Money money, LocalDateTime now) {
        money.perMinutePutMoney(now);
    }
}
