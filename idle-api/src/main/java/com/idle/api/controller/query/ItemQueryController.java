package com.idle.api.controller.query;

import com.idle.api.controller.dto.response.ResponseItemDto;
import com.idle.api.controller.sort.ItemsSort;
import com.idle.item.domain.Item;
import com.idle.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.*;
import static org.springframework.data.domain.Sort.*;
import static org.springframework.data.domain.Sort.Direction.*;

@RestController
@RequiredArgsConstructor
public class ItemQueryController {

    private final ItemRepository itemRepository;

    @GetMapping("/items/{memberId}")
    public List<ResponseItemDto> items(@PathVariable Long memberId, @RequestParam ItemsSort sort) {
        List<Item> items = itemRepository.findByMemberId(memberId, by(DESC, sort.condition()));

        return items.stream().map(ResponseItemDto::toDto).collect(toList());
    }

    @GetMapping("/item/{itemId}")
    public ResponseItemDto itemDetails(@PathVariable Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 아이템입니다."));

        return ResponseItemDto.toDto(item);
    }
}
