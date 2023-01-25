package com.idle.item.service;

import com.idle.item.domain.Item;
import com.idle.item.exception.NotWearItemException;
import com.idle.item.repository.ItemRepository;
import com.idle.item.service.dto.GradeUpDto;
import com.idle.item.service.dto.ResponseItemDto;
import com.idle.random.RandomGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.Sort.*;
import static org.springframework.data.domain.Sort.Direction.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    private final RandomGenerator randomGenerator;

    public ResponseItemDto upgrade(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 아이템 입니다."));

        item.upgrade(item.getMember().getMoney());

        return ResponseItemDto.toDto(item);
    }

    public GradeUpDto gradeUp(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 아이템 입니다."));

        item.gradeUp(item.getMember().getMoney(), randomGenerator.generate(1000));

        return GradeUpDto.toDto(item);
    }

    public ResponseItemDto synthesis(List<Long> itemIds) {
        List<Item> items = itemRepository.findByIds(itemIds, by(ASC, "weapon.grade"));

        Item legendaryItem = items.get(0).legendaryGradeUp(items);

        itemRepository.deleteAll(items);
        Item savedItem = itemRepository.save(legendaryItem);

        return ResponseItemDto.toDto(savedItem);
    }

    public ResponseItemDto starUp(List<Long> itemIds) {
        List<Item> items = itemRepository.findAllById(itemIds);
        Item legendary1 = items.get(0);
        Item legendary2 = items.get(1);

        legendary1.starUp(legendary2);

        itemRepository.delete(legendary2);

        return ResponseItemDto.toDto(legendary1);
    }

    public ResponseItemDto end(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 아이템입니다."));

        item.endGradeUp(item.getMember().getMoney());

        return ResponseItemDto.toDto(item);
    }

    public void itemWear(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 아이템입니다."));

        Item currentWearItem = itemRepository.findByWearingItems(item.getMember().getId())
                .orElseThrow(() -> new NotWearItemException("장착중인 아이템이 없습니다."));

        item.itemWear(currentWearItem);
    }
}
