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

    public void gradeUp(int random) {
        this.grade = this.grade.up(random);
    }

    public boolean gradeCheck(int index) {
        return this.grade != Grade.values()[index];
    }

    public boolean sameWeaponNameCheck(Weapon weapon) {
        return this.getName() == weapon.getName();
    }

    public boolean legendaryGradeCheck(Weapon legendary2) {
        return this.getGrade() == LEGENDARY && legendary2.getGrade() == LEGENDARY;
    }

    public void end() {
        this.grade = END;
    }
}
