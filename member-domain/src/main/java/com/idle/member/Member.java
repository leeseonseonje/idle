package com.idle.member;

import com.idle.money.Money;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "money_id")
    private Money money;

    private String email;

    @Builder
    private Member(String email) {
        this.money = new Money();
        this.email = email;
    }

    public static Member of(String email) {
        return Member.builder()
                .email(email)
                .build();
    }
}
