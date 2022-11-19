package com.idle.money;

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

    public void plusAmount() {
        this.amount += 1000;
    }
}
