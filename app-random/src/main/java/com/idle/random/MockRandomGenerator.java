package com.idle.random;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MockRandomGenerator implements RandomGenerator {

    private final int result;

    @Override
    public int generate(int bound) {
        return result;
    }
}
