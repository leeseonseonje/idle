package com.idle.item;

import com.idle.member.Member;
import com.idle.money.domain.Money;
import com.idle.weapon.domain.Weapon;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Embedded
    private Weapon weapon;

    private int upgrade;

    private int star;

    private boolean isWear;

    @Builder
    private Item(Member member, Weapon weapon) {
        this.member = member;
        this.weapon = weapon;
        this.upgrade = 0;
        this.star = 0;
        this.isWear = false;
    }

    public static Item of(Member member, Weapon weapon) {
        return Item.builder()
                .member(member)
                .weapon(weapon)
                .build();
    }

    public void upgrade(Money money) {
        money.payment(1000 + upgrade);
        this.upgrade++;
    }
}
