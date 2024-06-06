package com.reviewer.reviewer.services;
import com.reviewer.reviewer.dto.users.UserResponseDto;
import com.reviewer.reviewer.models.User;
import com.reviewer.reviewer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserResponseDto isInDatabase(UserResponseDto userDto){

        var user = repository.findById(userDto.id());
        if(user.isEmpty()){
            var userAdded = new User(userDto);
            repository.save(userAdded);
            return new UserResponseDto(userAdded.getId(), userAdded.getName(), userAdded.getEmail());
        }
        return new UserResponseDto(user.get().getId(), user.get().getName(), user.get().getEmail());
    }
    

}