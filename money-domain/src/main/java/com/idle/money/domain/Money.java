package com.idle.money.domain;

import com.idle.money.exception.ShortOfMoneyException;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Money {

    @Id
    @GeneratedValue
    private Long id;

    private int amount;

    public Money() {
        this.amount = 0;
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
}
