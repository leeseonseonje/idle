package com.idle.item.domain;

import com.idle.item.exception.SynthesisFailedException;
import com.idle.weapon.domain.Grade;
import com.idle.weapon.domain.Weapon;
import lombok.AllArgsConstructor;

import static com.idle.weapon.domain.Grade.LEGENDARY;

@AllArgsConstructor
public class MaterialWeaponInspector {

    private Weapon weapon;

    public MaterialWeaponInspector isSameName(Weapon weapon) {
        if (this.weapon.getName() != weapon.getName()) {
            throw new SynthesisFailedException("다른 종류의 무기는 합성할 수 없습니다.");
        }
        return this;
    }

    public MaterialWeaponInspector isMaterialGrade(Weapon weapon, int index) {
        if (weapon.getGrade() != Grade.values()[index]) {
            throw new SynthesisFailedException("등급이 맞지 않습니다.");
        }
        return this;
    }

    public MaterialWeaponInspector isUpgrade(Item item) {
        if (item.getUpgrade() < 100) {
            throw new SynthesisFailedException("업그레이드 횟수가 부족합니다.");
        }
        return this;
    }

    public MaterialWeaponInspector isLegendaryGrade(Weapon legendary2) {
        if (this.weapon.getGrade() != LEGENDARY && legendary2.getGrade() != LEGENDARY) {
            throw new SynthesisFailedException("레전더리 등급끼리만 합성이 가능합니다.");
        }
        return this;
    }

    public void end() {}
}