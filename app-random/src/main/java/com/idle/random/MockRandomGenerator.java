package com.idle.random;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public record MockRandomGenerator(int result) implements RandomGenerator {

    @Override
    public int generate(int bound) {
        return result;
    }
}
