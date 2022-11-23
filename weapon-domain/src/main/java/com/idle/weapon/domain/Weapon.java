package com.idle.weapon.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.*;
import static lombok.AccessLevel.*;

@Getter
@Embeddable
@NoArgsConstructor(access = PROTECTED)
public class Weapon {

    private Name name;

    @Enumerated(STRING)
    private Grade grade;

    @Builder
    private Weapon(Name name, Grade grade) {
        this.name = name;
        this.grade = grade;
    }

    public static Weapon of(Name name, Grade grade) {
        return Weapon.builder()
                .name(name)
                .grade(grade)
                .build();
    }

    public static Weapon type() {
        return Weapon.builder().build();
    }
}
