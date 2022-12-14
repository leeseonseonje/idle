package com.idle.api.controller;

import com.idle.member.Member;
import com.idle.member.repository.MemberRepository;
import com.idle.money.service.MoneyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class MoneyController {

    private final MoneyService moneyService;
    private final MemberRepository memberRepository;

    @PostMapping("/minute/money/{memberId}")
    public int perMinutePutMoney(@PathVariable Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        return moneyService.perMinutePutMoney(member.getMoney(), LocalDateTime.now());
    }
}