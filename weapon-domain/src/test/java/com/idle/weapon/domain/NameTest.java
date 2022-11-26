package com.idle.weapon.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.idle.weapon.domain.Name.*;
import static org.assertj.core.api.Assertions.*;

class NameTest {

    @Test
    @DisplayName("Name.values().length내의 값을 넘기면 해당 위치의 Name 반환 ")
    void name_index() {
        Name sword = get(0);
        Name spear = get(1);
        Name axe = get(2);

        assertThat(sword).isEqualTo(SWORD);
        assertThat(spear).isEqualTo(SPEAR);
        assertThat(axe).isEqualTo(AXE);
    }

}