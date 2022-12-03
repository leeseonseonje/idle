package com.idle.weapon.domain;

import com.idle.weapon.exception.GradeUpFailedException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Grade {

    NORMAL(r -> {
        if (r >= 990) {
            return valueOf("RARE");
        }
        throw new GradeUpFailedException("등급업 실패!");
    }),
    RARE(r -> {
        if (r >= 995) {
            return valueOf("EPIC");
        }
        throw new GradeUpFailedException("등급업 실패!");
    }),
    EPIC(r -> {
        if (r == 999) {
            return valueOf("UNIQUE");
        }
        throw new GradeUpFailedException("등급업 실패!");
    }),
    UNIQUE(r -> {
        throw new IllegalStateException("등급업 할 수 없는 등급입니다.");
    }),
    LEGENDARY(r -> {
        throw new IllegalStateException("등급업 할 수 없는 등급입니다.");
    }),
    END(r -> {
        throw new IllegalStateException("등급업 할 수 없는 등급입니다.");
    });

    private GradeUp gradeUp;

    public Grade up(int random) {
        return this.gradeUp.execute(random);
    }
}
