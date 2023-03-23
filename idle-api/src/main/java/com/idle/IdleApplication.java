package com.idle;

import com.idle.member.repository.MemberRepository;
import com.idle.money.repository.MoneyRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IdleApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdleApplication.class, args);
    }
}
