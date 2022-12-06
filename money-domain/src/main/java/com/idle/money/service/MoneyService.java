package com.idle.money.service;

import com.idle.money.domain.Money;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@Transactional
@RequiredArgsConstructor
public class MoneyService {

    public void putMoney(Money money, LocalDateTime now) {
        LocalDateTime lastCollectMoneyTime = money.getLastCollectMoneyTime();

        long betweenSeconds = ChronoUnit.SECONDS.between(lastCollectMoneyTime, now);
        long betweenMinute = betweenSeconds / 60;
        long remainingSeconds = betweenSeconds % 60;

        money.amountIncrease((int) (betweenMinute * 1000));

        LocalDateTime newLastCollectMoneyTime = now.minusSeconds(remainingSeconds);
        money.lastCollectMoneyTimeUpdate(newLastCollectMoneyTime);
    }
}
