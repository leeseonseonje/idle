package com.idle.money;

import com.idle.member.Member;
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

    private Money(Member member, int amount) {
        this.member = member;
        this.amount = amount;
    }

    public static Money of(Member member) {
        return new Money(member, 0);
    }

    public void plusAmount() {
        this.amount += 1000;
    }
}
