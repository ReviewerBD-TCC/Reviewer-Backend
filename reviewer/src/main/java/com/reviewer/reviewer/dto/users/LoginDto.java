package com.reviewer.reviewer.dto.users;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(

        @NotBlank
        String email,

        @NotBlank
        String password
) {
}
