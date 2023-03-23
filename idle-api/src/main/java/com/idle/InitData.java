package com.idle;

import com.idle.member.Member;
import com.idle.member.repository.MemberRepository;
import com.idle.money.repository.MoneyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitData {

    private final MemberRepository memberRepository;
    private final MoneyRepository moneyRepository;
    private final EntityManager em;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        System.out.println("initData");
        StringBuilder builder = new StringBuilder();
        builder.append("insert into money values");
        for (int i = 0; i < 100; i++) {
            if (i == 99) {
                builder.append("(").append(i).append(", 1000, null);");
            } else {
                builder.append("(").append(i).append(", 1000, null), ");
            }
        }
        em.createNativeQuery(builder.toString());
        em.flush();
        em.clear();
    }
}
