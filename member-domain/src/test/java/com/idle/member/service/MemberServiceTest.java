package com.idle.member.service;

import com.idle.member.Member;
import com.idle.member.exception.DuplicateNicknameException;
import com.idle.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void naming_unique() {
        MemberService sut = new MemberService(memberRepository);
        Member member = Member.builder().build();
        Member savedMember = memberRepository.save(member);

        String result = sut.naming(savedMember.getId(), "nickname");

        assertThat(result).isEqualTo("nickname");
    }

    @Test
    void naming_duplicate() {
        MemberService sut = new MemberService(memberRepository);
        Member existingMember = Member.builder().nickname("nickname").build();
        memberRepository.save(existingMember);
        Member member = Member.builder().build();
        Member savedMember = memberRepository.save(member);

        assertThatThrownBy(() -> sut.naming(savedMember.getId(), "nickname"))
                .isInstanceOf(DuplicateNicknameException.class);
    }
}