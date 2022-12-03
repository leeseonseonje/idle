package com.idle.item.domain;

import com.idle.member.Member;
import com.idle.money.domain.Money;
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
        this.weapon = this.weapon.gradeUp(random);
    }

    public Item legendaryGradeUp(List<Item> items) {
        MaterialWeaponDirector director = new MaterialWeaponDirector(this.weapon);
        director.checkLegendaryGradeUpMaterial(items);
        return Item.of(this.member, Weapon.of(this.weapon.getName(), LEGENDARY));
    }

    public void starUp(Item legendary2) {
        MaterialWeaponDirector director = new MaterialWeaponDirector(this.weapon);
        director.checkStarUpMaterial(legendary2);
        this.star++;
        this.upgrade += legendary2.getUpgrade();
    }

    public void endGradeUp(Money money) {
        money.payment(1000000);
        if (this.star >= 10) {
            this.weapon = this.weapon.endGradeUp();
        } else {
            throw new GradeUpFailedException("별이 부족합니다.");
        }
    }
}
