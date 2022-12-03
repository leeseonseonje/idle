package com.idle.item.domain;

import com.idle.item.exception.SynthesisFailedException;
import com.idle.weapon.domain.Grade;
import com.idle.weapon.domain.Weapon;
import com.idle.weapon.exception.GradeUpFailedException;
import lombok.AllArgsConstructor;

import java.util.List;

import static com.idle.weapon.domain.Grade.LEGENDARY;

@AllArgsConstructor
public class MaterialInspector {

    private Item item;

    protected void legendaryGradeUpMaterial(List<Item> items) {
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            isSameName(item.getWeapon());
            isUpgrade(item);
            isMaterialGrade(item.getWeapon(), i);
        }
    }

    protected void starUpMaterial(Item legendary2) {
        isSameName(legendary2.getWeapon());
        isLegendaryGrade(legendary2.getWeapon());
    }

    protected void endGradeUpMaterial() {
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

    private void isLegendaryGrade(Weapon legendary2) {
        if (this.item.getWeapon().getGrade() != LEGENDARY || legendary2.getGrade() != LEGENDARY) {
            throw new SynthesisFailedException("레전더리 등급끼리만 합성이 가능합니다.");
        }
    }
}