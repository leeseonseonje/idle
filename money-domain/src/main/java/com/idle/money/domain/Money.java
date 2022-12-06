package com.idle.money.domain;

import com.idle.money.exception.ShortOfMoneyException;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
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

    public void lastCollectMoneyTimeUpdate(LocalDateTime lastCollectMoneyTime) {
        this.lastCollectMoneyTime = lastCollectMoneyTime;
    }
}
