package com.idle.api.controller;

import com.idle.api.controller.dto.ItemSynthesisDto;
import com.idle.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PatchMapping("/item/upgrade/{itemId}")
    public void upgrade(@PathVariable Long itemId) {
        itemService.upgrade(itemId);
    }

    @PostMapping("/item/grade/{itemId}")
    public void gradeUp(@PathVariable Long itemId) {
        itemService.gradeUp(itemId);
    }

    @PostMapping("/item/synthesis")
    public void synthesis(@RequestBody ItemSynthesisDto request) {
        List<Long> ids = List.of(request.normalId(), request.rareId(), request.epicId(), request.uniqueId());

        itemService.synthesis(ids);
    }

    @PostMapping("/item/star/{legendary1}/{legendary2}")
    public void starUp(@PathVariable Long legendary1, @PathVariable Long legendary2) {
        itemService.starUp(List.of(legendary1, legendary2));
    }

    @PostMapping("/item/end/{itemId}")
    public void end(@PathVariable Long itemId) {
        itemService.end(itemId);
    }
}
