package com.idle.oauth.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record
ResponseToken(
        @JsonProperty("token_type") String tokenType,
        @JsonProperty("access_token") String accessToken,
        @JsonProperty("refresh_token") String refreshToken,
        @JsonProperty("expires_in") Integer expiresIn,
        @JsonProperty("refresh_token_expires_in") Integer refreshTokenExpiresIn
) {
}
