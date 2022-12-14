package com.idle.api.controller.dto;

import java.util.List;

public record ItemSynthesisDto(
        Long normalId,
        Long rareId,
        Long epicId,
        Long uniqueId
) {

    public List<Long> toList() {
        return List.of(normalId, rareId, epicId, uniqueId);
    }
}
