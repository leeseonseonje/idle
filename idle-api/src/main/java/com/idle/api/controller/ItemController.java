package com.idle.api.controller;

import com.idle.api.controller.dto.request.RequestItemSynthesisDto;
import com.idle.item.service.ItemService;
import com.idle.item.service.dto.GradeUpDto;
import com.idle.item.service.dto.ResponseItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PatchMapping("/upgrade/{itemId}")
    public ResponseItemDto upgrade(@PathVariable Long itemId) {
        return itemService.upgrade(itemId);
    }

    @PatchMapping("/grade/{itemId}")
    public GradeUpDto gradeUp(@PathVariable Long itemId) {
        return itemService.gradeUp(itemId);
    }

    @PostMapping("/synthesis")
    public ResponseItemDto synthesis(@RequestBody RequestItemSynthesisDto request) {
        return itemService.synthesis(request.toList());
    }

    @PostMapping("/star/{legendary1}/{legendary2}")
    public ResponseItemDto starUp(@PathVariable Long legendary1, @PathVariable Long legendary2) {
        return itemService.starUp(List.of(legendary1, legendary2));
    }

    @PatchMapping("/end/{itemId}")
    public ResponseItemDto end(@PathVariable Long itemId) {
        return itemService.end(itemId);
    }

    @PatchMapping("/wear/{itemId}")
    public void wearingItems(@PathVariable Long itemId) {
        itemService.itemWear(itemId);
    }
}
