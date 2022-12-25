package com.idle.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @GetMapping("/oauth")
    public void oauth(@RequestParam String code) {
        System.out.println("code = " + code);
    }
}
