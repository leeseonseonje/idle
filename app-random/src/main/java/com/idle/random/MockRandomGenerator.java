package com.idle.random;

public record MockRandomGenerator(int result) implements RandomGenerator {

    @Override
    public int generate(int bound) {
        return result;
    }
}
