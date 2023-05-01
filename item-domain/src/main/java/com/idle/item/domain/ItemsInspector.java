package com.idle.item.domain;

import com.idle.item.exception.SynthesisFailedException;
import com.idle.weapon.domain.Grade;
import com.idle.weapon.domain.Weapon;
import com.idle.weapon.exception.GradeUpFailedException;
import lombok.AllArgsConstructor;

import java.util.List;

import static com.idle.weapon.domain.Grade.END;
import static com.idle.weapon.domain.Grade.LEGENDARY;

@AllArgsConstructor
public class ItemsInspector {

    private Item item;

    protected void checkLegendaryGradeUp(List<Item> items) {
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            isSameName(item.getWeapon());
            isUpgrade(item);
            isMaterialGrade(item.getWeapon(), i);
        }
    }

    protected void checkStarUp(Item item2) {
        isSameName(item2.getWeapon());
        isLegendaryGradeOrHigher(item2.getWeapon());
    }

    protected void checkEndGradeUp() {
        if (this.item.getStar() < 10) {
            throw new GradeUpFailedException("별이 부족합니다.");
        }
    }

    private void isSameName(Weapon weapon) {
        if (this.item.getWeapon().getName() != weapon.getName()) {
            throw new SynthesisFailedException("다른 종류의 무기는 합성할 수 없습니다.");
        }
    }

    private void isUpgrade(Item item) {
        if (item.getUpgrade() < 100) {
            throw new SynthesisFailedException("업그레이드 횟수가 부족합니다.");
        }
    }

    private void isMaterialGrade(Weapon weapon, int index) {
        if (weapon.getGrade() != Grade.values()[index]) {
            throw new SynthesisFailedException("등급이 맞지 않습니다.");
        }
    }

    private void isLegendaryGradeOrHigher(Weapon item2) {
        if (this.item.getWeapon().getGrade() != LEGENDARY || item2.getGrade() != LEGENDARY) {
            if (this.item.getWeapon().getGrade() != END || item2.getGrade() != END)
            throw new SynthesisFailedException("레전더리등급 이상의 같은 등급끼리만 합성이 가능합니다.");
        }
    }
}