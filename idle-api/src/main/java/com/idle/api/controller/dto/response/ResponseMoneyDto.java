package com.idle.api.controller.dto.response;

import com.idle.money.domain.Money;

import java.time.LocalDateTime;

public record ResponseMoneyDto(
        int amount,
        LocalDateTime lastCollectMoneyTime
) {

    public static ResponseMoneyDto createDto(Money money) {
        return new ResponseMoneyDto(money.getAmount(), money.getLastCollectMoneyTime());
    }
}
