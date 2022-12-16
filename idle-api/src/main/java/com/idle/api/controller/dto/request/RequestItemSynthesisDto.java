package com.idle.api.controller.dto.request;

import java.util.List;

public record RequestItemSynthesisDto(
        Long normalId,
        Long rareId,
        Long epicId,
        Long uniqueId
) {

    public List<Long> toList() {
        return List.of(normalId, rareId, epicId, uniqueId);
    }
}
