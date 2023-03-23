package com.idle;

import org.junit.jupiter.api.Test;

class InitDataTest {

    @Test
    void test() {
        StringBuilder builder = new StringBuilder();
        builder.append("insert into money (amount) values");
        for (int i = 0; i < 30000; i++) {
            if (i == 29999) {
                builder.append("(1000);");
            } else {
                builder.append("(1000), ");
            }
        }
        System.out.println(builder);
    }
}