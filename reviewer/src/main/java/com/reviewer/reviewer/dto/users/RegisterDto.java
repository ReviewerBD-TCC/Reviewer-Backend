package com.reviewer.reviewer.dto.users;

import com.reviewer.reviewer.dto.users.Enums.TypeRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDto (

        @NotBlank
        String name,

        @NotBlank
        String email,

        @NotBlank
        String password,

        @NotBlank
        String user,

        @NotNull
        TypeRole type,

        @NotBlank
        String gkz,

        @NotBlank
        String manager ){
}
