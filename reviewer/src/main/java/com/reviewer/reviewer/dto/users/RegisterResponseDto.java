package com.reviewer.reviewer.dto.users;

import com.reviewer.reviewer.dto.users.Enums.TypeRole;
import com.reviewer.reviewer.models.User;

public record RegisterResponseDto (Long id, String name, String email, String user, TypeRole type, String gkz, String manager){
    public RegisterResponseDto(User register) {
        this(register.getId(), register.getName(), register.getEmail(), register.getUser(), register.getType(), register.getGkz(), register.getManager());
    }
}
