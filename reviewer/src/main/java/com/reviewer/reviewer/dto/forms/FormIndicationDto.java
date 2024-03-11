package com.reviewer.reviewer.dto.forms;

import com.reviewer.reviewer.models.IndicatedUsers;
import com.reviewer.reviewer.models.User;
import jakarta.validation.Valid;

import java.util.List;

public record
FormIndicationDto(
        Long id,
        Long indicatingUser,
        List<IndicatedUserDto> indicatedUsers
) {
//    public FormIndicationDto(FormIndicationResponseDto formIndicated) {
//        this(formIndicated.id(), formIndicated.indicatingUser(), formIndicated.indicatedUsers());
//    }
}
