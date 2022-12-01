package com.idle.item.domain;

import com.idle.item.exception.SynthesisFailedException;
import com.idle.member.Member;
import com.idle.money.domain.Money;
import com.idle.weapon.domain.Grade;
import com.idle.weapon.domain.Weapon;
import com.idle.weapon.exception.GradeUpFailedException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

import static com.idle.weapon.domain.Grade.*;
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

    public void gradeUp(Money money, int random) {
        money.payment(1000);
        this.weapon.gradeUp(random);
    }

    public Weapon synthesis(List<Item> items) {
        ingredientCheck(items);
        return Weapon.of(this.getWeapon().getName(), LEGENDARY);
    }

    private void ingredientCheck(List<Item> items) {
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (this.weapon.sameWeaponNameCheck(item.weapon)) {
                throw new SynthesisFailedException("다른 종류의 무기는 합성할 수 없습니다.");
            }

            if (item.getUpgrade() < 100) {
                throw new SynthesisFailedException("업그레이드 횟수가 모자릅니다.");
            }

            if (item.weapon.gradeCheck(i)) {
                throw new SynthesisFailedException("등급이 맞지 않습니다.");
            }
        }
    }

    public void legendarySynthesis(Item legendary2) {
        if (!this.weapon.legendaryGradeCheck(legendary2.weapon)) {
            throw new SynthesisFailedException("레전더리 등급끼리만 합성이 가능합니다.");
        }
        if (!this.weapon.sameWeaponNameCheck(legendary2.weapon)) {
            throw new SynthesisFailedException("다른 종류의 무기는 합성할 수 없습니다.");
        }
        this.star++;
        this.upgrade += legendary2.getUpgrade();
    }

    public void end(Money money) {
        money.payment(1000000);
        if (this.star >= 10) {
            this.weapon.end();
        } else {
            throw new GradeUpFailedException("별이 부족합니다.");
        }
    }
}
