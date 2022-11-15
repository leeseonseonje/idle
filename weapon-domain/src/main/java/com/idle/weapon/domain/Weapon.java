package com.idle.weapon.domain;

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

    private String name;

    @Enumerated(STRING)
    private Grade grade;
}
