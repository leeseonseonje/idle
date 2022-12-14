package com.idle.weapon.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.idle.weapon.domain.Grade.*;
import static javax.persistence.EnumType.*;
import static lombok.AccessLevel.*;

@Getter
@Embeddable
@NoArgsConstructor(access = PROTECTED)
public class Weapon {

    @Enumerated(STRING)
    private Name name;

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

    public static Weapon randomWeapon(int random) {
        Name name = Name.get(random);
        return Weapon.of(name, NORMAL);
    }

    public Weapon gradeUp(int random) {
        return Weapon.of(this.name, this.grade.up(random));
    }

    public Weapon endGradeUp() {
        return Weapon.of(this.name, END);
    }
}
