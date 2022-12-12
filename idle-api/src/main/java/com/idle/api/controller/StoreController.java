package com.idle.api.controller;

import com.idle.api.controller.dto.StorePurchaseDto;
import com.idle.api.service.StoreApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreApiService storeApiService;

    @PostMapping("/store/weapon")
    public void weaponPurchase(@RequestBody StorePurchaseDto request) {
        storeApiService.weaponPurchase(request);
    }
}
