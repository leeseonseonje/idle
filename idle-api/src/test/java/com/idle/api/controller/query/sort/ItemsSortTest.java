package com.idle.api.controller.query.sort;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ItemsSortTest {

    @Test
    void sort_split_test() {
        String[] nameConditions = ItemsSort.NAME.conditions();
        String[] gradeConditions = ItemsSort.GRADE.conditions();

        assertThat(nameConditions).containsExactly("weapon.name", "weapon.grade", "star", "upgrade");
        assertThat(gradeConditions).containsExactly("weapon.grade", "star", "upgrade");
    }

}