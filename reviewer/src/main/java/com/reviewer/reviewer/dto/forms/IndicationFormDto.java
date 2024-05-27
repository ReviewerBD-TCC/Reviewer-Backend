package com.reviewer.reviewer.dto.forms;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record IndicationFormDto(

        @NotNull
        Long userIndication,
        Long formId,

        @NotNull
        List<IndicatedDto> indicateds
) {
}
