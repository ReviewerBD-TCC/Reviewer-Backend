package com.reviewer.reviewer.dto.forms;

import jakarta.validation.constraints.NotNull;

public record IndicatedDto(

        @NotNull
        Long userIndicated
) {
}
