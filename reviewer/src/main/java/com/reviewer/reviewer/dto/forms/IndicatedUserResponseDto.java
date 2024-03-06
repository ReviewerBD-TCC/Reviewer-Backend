package com.reviewer.reviewer.dto.forms;

import com.reviewer.reviewer.models.FormIndication;
import com.reviewer.reviewer.models.IndicatedUsers;

public record IndicatedUserResponseDto(
        Long id,
        String name
) {
    public IndicatedUserResponseDto(IndicatedUsers indicatedUser) {
        this(indicatedUser.getId(), indicatedUser.getUserIndicated().getName());
    }
}
