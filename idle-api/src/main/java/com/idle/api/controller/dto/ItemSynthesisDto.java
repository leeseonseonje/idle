package com.idle.api.controller.dto;

public record ItemSynthesisDto(
        Long normalId,
        Long rareId,
        Long epicId,
        Long uniqueId
) {
}
