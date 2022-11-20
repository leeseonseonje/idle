package com.idle.member;

import com.idle.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringBootApplicationTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void test() {
        Member member = memberRepository.findById(1L).orElseGet(Member::new);
        System.out.println(member);
    }

}
