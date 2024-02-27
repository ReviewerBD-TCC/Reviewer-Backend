package com.reviewer.reviewer.dto.users;

import com.reviewer.reviewer.models.User;

public record RegisterResponse (long id, String name, String email, String user, String type, String gkz, String manager){
    public RegisterResponse(User register) {
        this(register.getId(), register.getName(), register.getEmail(), register.getUser(), register.getType(), register.getGkz(), register.getManager());
    }
}
