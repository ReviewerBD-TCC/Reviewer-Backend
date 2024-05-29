package com.reviewer.reviewer.dto.users;

import com.reviewer.reviewer.models.User;
import jakarta.validation.constraints.NotBlank;

public record UserDto(
        String id,
        String name,
        String mail

){

    public UserDto(User user) {
        this(user.getId(),user.getName(), user.getEmail());
    }
}