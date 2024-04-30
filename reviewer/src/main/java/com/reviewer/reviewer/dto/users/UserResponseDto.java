package com.reviewer.reviewer.dto.users;

import com.reviewer.reviewer.dto.users.Enums.TypeRole;
import com.reviewer.reviewer.models.User;

public record UserResponseDto(Long id, String name, String email, String gkz, TypeRole type, String user, String manager) {

    public UserResponseDto(User user) {
        this(user.getId(), user.getName(),  user.getEmail(), user.getGkz(), user.getType(), user.getUser(), user.getManager());
    }
}
