package com.idle.member;

import com.idle.money.domain.Money;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "money_id")
    private Money money;

    private String nickname;

    private Long oauthId;

    private String accessToken;

    private String refreshToken;

    @Builder
    public Member(Long id, String nickname, Long oauthId, String accessToken, String refreshToken) {
        this.id = id;
        this.money = Money.of(0, LocalDateTime.now());
        this.nickname = nickname;
        this.oauthId = oauthId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static Member simple(String nickname) {
        return Member.builder()
                .nickname(nickname)
                .build();
    }

    public static Member newMember(Long oauthId, String accessToken, String refreshToken) {
        return Member.builder()
                .oauthId(oauthId)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public void naming(String nickname) {
        this.nickname = nickname;
    }

    public void tokenReissue(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        if (StringUtils.hasText(refreshToken)) {
            this.refreshToken = refreshToken;
        }
    }
}
