package com.reviewer.reviewer.dto.users;

import com.auth0.jwt.JWT;
import com.reviewer.reviewer.dto.users.Enums.TypeRole;
import com.reviewer.reviewer.models.User;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Map;

public record UserResponseDto(String id, String name, String mail) {

    public UserResponseDto(Jwt jwtUser) {
        this(jwtUser.getClaimAsString("id"), jwtUser.getClaimAsString("name"), jwtUser.getClaimAsString("mail"));
    }

}
