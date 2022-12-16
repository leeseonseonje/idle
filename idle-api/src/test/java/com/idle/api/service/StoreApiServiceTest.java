package com.idle.api.service;

import com.idle.api.controller.dto.request.RequestStorePurchaseDto;
import com.idle.api.service.dto.StoreItemDto;
import com.idle.member.Member;
import com.idle.member.repository.MemberRepository;
import com.idle.shop.domain.weapon.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class StoreApiServiceTest {

    @Autowired
    StoreApiService sut;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void weapon_purchase_item_save() {
        Member member = Member.of("email@email.com");
        member.getMoney().amountIncrease(99999);
        memberRepository.save(member);

        RequestStorePurchaseDto dto = new RequestStorePurchaseDto(1L, Product.RANDOM_WEAPON_BOX);
        StoreItemDto result = sut.weaponPurchase(dto.memberId(), dto.product());

        System.out.println(result.weapon().getName());
        System.out.println(result.weapon().getGrade());
        assertThat(result.itemId()).isEqualTo(3L);
    }
}