package com.idle.money;

import com.idle.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Money {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private int amount;

    @Builder
    private Money(Member member) {
        this.member = member;
        this.amount = 0;
    }

    public static Money of(Member member) {
        return Money.builder()
                .member(member)
                .build();
    }

    public void plusAmount() {
        this.amount += 1000;
    }
}
