package com.reviewer.reviewer.services;
import com.reviewer.reviewer.dto.users.UserDetailsResponseDto;
import com.reviewer.reviewer.models.User;
import com.reviewer.reviewer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email);
    }


    public List<UserDetailsResponseDto> findAll(){
        var users = repository.findAll();
        List<UserDetailsResponseDto> userDto = new ArrayList<>();
        for (User user : users) {
            var userResponse = new UserDetailsResponseDto(user);
            userDto.add(userResponse);
        }

        return userDto;
    }

    public UserDetailsResponseDto findMe(Principal principal) {
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        return new UserDetailsResponseDto(user);
    }

}