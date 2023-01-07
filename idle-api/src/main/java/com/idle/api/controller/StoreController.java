package com.idle.api.controller;

import com.idle.api.controller.dto.request.RequestStorePurchaseDto;
import com.idle.api.service.StoreApiService;
import com.idle.api.service.dto.StoreItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreApiService storeApiService;

    @PostMapping("/stores/weapon")
    public StoreItemDto weaponPurchase(@RequestBody RequestStorePurchaseDto request) {
        return storeApiService.weaponPurchase(request.memberId(), request.product());
    }
}
