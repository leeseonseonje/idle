package com.idle.api.service;

import com.idle.api.controller.dto.request.RequestStorePurchaseDto;
import com.idle.api.service.dto.StoreItemDto;
import com.idle.item.domain.Item;
import com.idle.item.repository.ItemRepository;
import com.idle.member.Member;
import com.idle.member.repository.MemberRepository;
import com.idle.shop.domain.weapon.Product;
import com.idle.shop.service.StoreService;
import com.idle.weapon.domain.Weapon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreApiService {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final StoreService storeService;

    public StoreItemDto weaponPurchase(Long memberId, Product product) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        Weapon purchaseWeapon = storeService.weaponPurchase(member.getMoney(), product);

        Item item = Item.of(member, purchaseWeapon, 0, 0, false);
        Item savedItem = itemRepository.save(item);
        return new StoreItemDto(savedItem.getId(), savedItem.getWeapon());
    }
}
