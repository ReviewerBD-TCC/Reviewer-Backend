package com.reviewer.reviewer.dto.users;

import com.reviewer.reviewer.dto.users.Enums.TypeRole;
import com.reviewer.reviewer.models.User;

public record UserResponseDto(Long id, String email, String name, String gkz, TypeRole type, String user) {

    public UserResponseDto(User user) {
        this(user.getId(), user.getEmail(), user.getName(), user.getGkz(), user.getType(), user.getUser());
    }
}