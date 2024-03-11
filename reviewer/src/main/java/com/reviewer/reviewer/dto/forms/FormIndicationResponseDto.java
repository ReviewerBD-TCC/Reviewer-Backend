package com.reviewer.reviewer.dto.forms;

import com.reviewer.reviewer.models.FormIndication;

import java.util.List;

public record FormIndicationResponseDto(
        Long id,
        Long indicatingUser,
        List<IndicatedUserDto> indicatedUsers
) {
//    public FormIndicationResponseDto(FormIndication formIndicated, IndicatedUserDto indicated) {
//        this(formIndicated.getId(), formIndicated.getIndicatingUser().getId(), indicated);
//    }

    public FormIndicationResponseDto(FormIndicationDto form) {
        this(form.id(), form.indicatingUser(), form.indicatedUsers());
    }
}
