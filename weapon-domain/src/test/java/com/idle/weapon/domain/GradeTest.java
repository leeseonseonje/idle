package com.idle.weapon.domain;

import com.idle.weapon.exception.GradeUpFailedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.idle.weapon.domain.Grade.*;
import static org.assertj.core.api.Assertions.*;

public class GradeTest {

    @Test
    @DisplayName("넘겨받은 랜덤 값이 범위 안에 일치하면 다음 등급 반환")
    void grade_up() {
        Grade rare = NORMAL.up(990);
        Grade epic = rare.up(995);
        Grade unique = epic.up(999);

        assertThat(rare).isEqualTo(RARE);
        assertThat(epic).isEqualTo(EPIC);
        assertThat(unique).isEqualTo(UNIQUE);
    }

    @Test
    @DisplayName("넘겨받은 값이 범위 안에 없으면 GradeUpFailedException 발생")
    void random_mismatch_gradeUpFailedException() {
        assertThatThrownBy(() -> NORMAL.up(900))
                .isInstanceOf(GradeUpFailedException.class);
    }
}
