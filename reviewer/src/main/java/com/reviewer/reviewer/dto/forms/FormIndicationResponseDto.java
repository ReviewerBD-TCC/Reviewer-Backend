package com.reviewer.reviewer.dto.forms;

import com.reviewer.reviewer.models.FormIndication;

public record FormIndicationResponseDto(
        Long id,
        Long indicatingUser,
        IndicatedUserDto indicatedUsers
) {
    public FormIndicationResponseDto(FormIndication formIndicated, IndicatedUserDto indicated) {
        this(formIndicated.getId(), formIndicated.getIndicatingUser().getId(), indicated);
    }
}
