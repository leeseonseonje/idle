package com.idle.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    @Builder
    private Member(String email) {
        this.email = email;
    }

    public static Member of(String email) {
        return Member.builder()
                .email(email)
                .build();
    }
}
