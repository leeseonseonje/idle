package com.idle.random;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RandomGeneratorTest {

    @Test
    @DisplayName("mockRandomGenerator은 인스턴스 생성시 넘겨주는 값을 반환한다.")
    void args_int_return() {
        RandomGenerator randomGenerator = new MockRandomGenerator(100);
        assertThat(randomGenerator.generate(0)).isEqualTo(100);
    }

}