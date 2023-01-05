package com.idle.member.service;

import com.idle.member.Member;
import com.idle.member.exception.DuplicateNicknameException;
import com.idle.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public String naming(Long memberId, String nickname) {

        nicknameDuplicateCheck(nickname);

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        member.naming(nickname);

        return nickname;
    }

    private void nicknameDuplicateCheck(String nickname) {
        boolean isDuplicate = memberRepository.existsByNickname(nickname);

        if (isDuplicate) {
            throw new DuplicateNicknameException("이미 사용중인 이름입니다.");
        }
    }

    public void logout(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        member.logout();
    }
}
