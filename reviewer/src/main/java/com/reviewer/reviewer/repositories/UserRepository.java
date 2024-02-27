package com.reviewer.reviewer.repositories;

<<<<<<< HEAD
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository {
    UserDetails findByLogin(String login);
=======
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
>>>>>>> keven
}
