package com.idle.weapon.domain;

import com.idle.weapon.NAME;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Weapon {

    @Id
    @GeneratedValue
    private Long id;

    private NAME name;

    @Enumerated(STRING)
    private Grade grade;

    @Builder
    private Weapon(NAME name, Grade grade) {
        this.name = name;
        this.grade = grade;
    }

    public static Weapon of(NAME name, Grade grade) {
        return Weapon.builder()
                .name(name)
                .grade(grade)
                .build();
    }
}
