package com.idle.weapon.domain;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Grade {

    NORMAL(1),
    RARE(2),
    EPIC(3),
    UNIQUE(4),
    LEGENDARY(5),
    END(6);

    private Integer number;

    private static final Map<Integer, Grade> map = Arrays.stream(values())
            .collect(Collectors.toMap(g -> g.number, Function.identity()));
    Grade(int number) {
        this.number = number;
    }

    public Grade from(Integer number) {
        Grade grade = map.getOrDefault(number, null);
        if (grade == null) {
            throw new IllegalStateException("꽝");
        }
        return grade;
    }
}
