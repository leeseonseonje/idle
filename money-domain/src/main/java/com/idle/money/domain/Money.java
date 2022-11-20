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

    public void plusAmount(int amount) {
        this.amount += amount;
    }

    public void payment(int amount) {
        balanceCheck(amount);
        this.amount -= amount;
    }

    private void balanceCheck(int amount) {
        if (this.amount < amount) {
            throw new ShortOfMoneyException("돈이 부족합니다.");
        }
    }
}
