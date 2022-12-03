package com.idle.item.domain;

import com.idle.weapon.domain.Weapon;
import lombok.AllArgsConstructor;

import java.util.List;


public class MaterialWeaponDirector {

    private MaterialWeaponInspector inspector;

    public MaterialWeaponDirector(Weapon weapon) {
        this.inspector = new MaterialWeaponInspector(weapon);
    }

    public void checkLegendaryGradeUpMaterial(List<Item> items) {
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            inspector.isSameName(item.getWeapon())
                    .isUpgrade(item)
                    .isMaterialGrade(item.getWeapon(), i)
                    .end();
        }
    }

    public void checkStarUpMaterial(Item legendary2) {
        inspector.isSameName(legendary2.getWeapon())
                .isLegendaryGrade(legendary2.getWeapon())
                .end();
    }
}
