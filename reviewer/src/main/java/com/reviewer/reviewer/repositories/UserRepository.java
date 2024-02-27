package com.reviewer.reviewer.repositories;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository {
    UserDetails findByLogin(String login);
}
