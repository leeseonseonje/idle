package com.idle.api.controller.query;

import com.idle.api.controller.dto.response.ResponseStoreDto;
import com.idle.shop.domain.weapon.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;

@RestController
public class StoreQueryController {

    @GetMapping("/stores/weapon/products")
    public List<ResponseStoreDto> weaponProducts() {
        return stream(Product.values()).map(ResponseStoreDto::toDto).collect(toList());
    }
}
