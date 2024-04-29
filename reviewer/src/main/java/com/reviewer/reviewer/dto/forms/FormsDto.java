package com.reviewer.reviewer.dto.forms;
import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;

public record FormsDto(

        @NotNull
        LocalDate year,

        @NotNull
        LocalDate validation
) {

}
