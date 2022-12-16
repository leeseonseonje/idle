package com.idle.api.controller.query;

import com.idle.api.controller.dto.response.ResponseMoneyDto;
import com.idle.member.Member;
import com.idle.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MoneyQueryController {

    private final MemberRepository memberRepository;

    @GetMapping("/money/{memberId}")
    public ResponseMoneyDto money(@PathVariable Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        return ResponseMoneyDto.toDto(member.getMoney());
    }
}
