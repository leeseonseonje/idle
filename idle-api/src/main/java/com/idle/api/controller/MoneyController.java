package com.idle.api.controller;

import com.idle.member.Member;
import com.idle.member.repository.MemberRepository;
import com.idle.money.service.MoneyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/money")
@RequiredArgsConstructor
public class MoneyController {

    private final MoneyService moneyService;
    private final MemberRepository memberRepository;

    @GetMapping("/{memberId}")
    public int perMinutePutMoney(@PathVariable Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        return moneyService.perMinutePutMoney(member.getMoney(), LocalDateTime.now());
    }
}
