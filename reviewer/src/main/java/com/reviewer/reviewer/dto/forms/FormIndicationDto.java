package com.reviewer.reviewer.dto.forms;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record FormIndicationDto(

        @NotNull
        Long userIndication,

        @NotNull
        List<IndicatedDto> indicados
) {
}
