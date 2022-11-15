package com.idle.money.repository;

import com.idle.money.Money;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MoneyRepository extends JpaRepository<Money, Long> {

    @Query("select m from Money m where m.member.id = :memberId")
    Money findByMemberId(@Param("memberId") Long memberId);
}
