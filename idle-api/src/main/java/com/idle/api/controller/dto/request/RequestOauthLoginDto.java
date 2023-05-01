package com.idle.api.controller.dto.request;

public record RequestOauthLoginDto(
        String code,
        OauthServer server
) {
}
