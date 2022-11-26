package com.idle.weapon.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.idle.weapon.domain.Grade.*;
import static org.assertj.core.api.Assertions.*;

public class GradeTest {

    @Test
    @DisplayName("인자로 넘긴 정수의 범위 내에 있는 등급을 반환한다.")
    void random_grade() {
        Grade normal = randomGrade(500);
        Grade rare = randomGrade(790);
        Grade epic = randomGrade(895);
        Grade unique = randomGrade(999);
        Grade boom = randomGrade(400);

        assertThat(normal).isEqualTo(NORMAL);
        assertThat(rare).isEqualTo(RARE);
        assertThat(epic).isEqualTo(EPIC);
        assertThat(unique).isEqualTo(UNIQUE);
        assertThat(boom).isNull();
    }
}
