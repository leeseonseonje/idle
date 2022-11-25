package com.idle.item.service;

import com.idle.item.Item;
import com.idle.item.repository.ItemRepository;
import com.idle.weapon.domain.Weapon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void upgrade(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 아이템 입니다."));

        item.upgrade(item.getMember().getMoney());
    }

    public void synthesis(List<Long> itemIds) {
        List<Item> items = itemRepository.findAllById(itemIds);

        Weapon legendaryWeapon = items.get(0).synthesis(items);
    }
}
