package com.idle.money.domain;

import com.idle.money.exception.ShortOfMoneyException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Money {

    @Id
    @GeneratedValue
    private Long id;

    private int amount;

    private LocalDateTime lastCollectMoneyTime;

    @Builder
    private Money(int amount, LocalDateTime lastCollectMoneyTime) {
        this.amount = amount;
        this.lastCollectMoneyTime = lastCollectMoneyTime;
    }

    public static Money of(int amount, LocalDateTime lastCollectMoneyTime) {
        return Money.builder()
                .amount(amount)
                .lastCollectMoneyTime(lastCollectMoneyTime)
                .build();
    }

    public void amountIncrease(int amount) {
        this.amount += amount;
    }

    public void payment(int price) {
        balanceCheck(price);
        this.amount -= price;
    }

    private void balanceCheck(int price) {
        if (this.amount < price) {
            throw new ShortOfMoneyException("돈이 부족합니다.");
        }
    }

    public int perMinutePutMoney(LocalDateTime now) {
        long betweenSeconds = SECONDS.between(this.lastCollectMoneyTime, now);
        long betweenMinute = betweenSeconds / 60;
        long remainingSeconds = betweenSeconds % 60;

        int depositedAmount = (int) betweenMinute * 1000;
        this.amountIncrease(depositedAmount);

        this.lastCollectMoneyTime = now.minusSeconds(remainingSeconds);

        return depositedAmount;
    }
}
