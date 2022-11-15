package com.idle.money.service;

import com.idle.money.Money;
import com.idle.money.repository.MoneyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MoneyService {

    private final MoneyRepository moneyRepository;

    public void idlePlusMoney(Long memberId) {
        Money money = moneyRepository.findByMemberId(memberId);
        money.plusAmount();
    }
}
