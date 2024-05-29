package com.reviewer.reviewer.dto.users;

import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Map;

public record UserResponseDto(String id, String name, String mail) {

    public UserResponseDto(Jwt jwtUser) {
        this(jwtUser.getClaimAsString("oid"), jwtUser.getClaimAsString("name"), jwtUser.getClaimAsString("preferred_username"));
    }

}
