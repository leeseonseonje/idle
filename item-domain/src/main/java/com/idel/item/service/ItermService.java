package com.idel.item.service;

import com.idel.item.Item;
import com.idel.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItermService {

    private final ItemRepository itemRepository;

    public void upgrade(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 아이템 입니다."));

        item.upgrade();
    }
}
