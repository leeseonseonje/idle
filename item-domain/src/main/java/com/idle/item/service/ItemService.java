package com.idle.item.service;

import com.idle.item.domain.Item;
import com.idle.item.repository.ItemRepository;
import com.idle.random.RandomGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.data.domain.Sort.*;
import static org.springframework.data.domain.Sort.Direction.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    private final RandomGenerator randomGenerator;
    public void upgrade(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 아이템 입니다."));

        item.upgrade(item.getMember().getMoney());
    }

    public void gradeUp(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 아이템 입니다."));

        item.gradeUp(item.getMember().getMoney(), randomGenerator.generate(1000));
    }

    public Item synthesis(List<Long> itemIds) {
        List<Item> items = itemRepository.findByIds(itemIds, by(ASC, "weapon.grade"));

        Item legendaryItem = items.get(0).synthesis(items);

        itemRepository.deleteAll(items);
        return itemRepository.save(legendaryItem);
    }

    public void starUp(List<Long> itemIds) {
        List<Item> items = itemRepository.findAllById(itemIds);
        Item legendary1 = items.get(0);
        Item legendary2 = items.get(1);

        legendary1.starUp(legendary2);

        itemRepository.delete(legendary2);
    }

    public void end(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 아이템입니다."));

        item.end(item.getMember().getMoney());
    }
}
