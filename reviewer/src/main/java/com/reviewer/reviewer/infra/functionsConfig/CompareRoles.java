package com.reviewer.reviewer.infra.functionsConfig;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompareRoles {
    public String compareRoles(Jwt jwtToken) {
        List<String> roles = jwtToken.getClaimAsStringList("roles");

        System.out.println(roles);

        if (roles.contains("User.Read")) {
            return "ROLE_ADMIN";
        } else if (roles.contains("BDUSERSWD")) {
            return "ROLE_BDUSERSWD";
        } else {
            return "ROLE_USER";
        }
    }
}
