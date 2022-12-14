package com.idle.member.repository;

import com.idle.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByOauthId(Long oauthId);

    Optional<Member> findByAccessToken(String accessToken);

    boolean existsByNickname(String nickName);
}
