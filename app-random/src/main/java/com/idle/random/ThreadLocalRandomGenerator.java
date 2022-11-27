package com.idle.random;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class ThreadLocalRandomGenerator implements RandomGenerator {

    @Override
    public int generate(int bound) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextInt(bound);
    }
}
