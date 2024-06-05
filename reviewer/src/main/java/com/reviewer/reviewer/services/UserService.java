package com.reviewer.reviewer.services;
import com.reviewer.reviewer.dto.users.UserResponseDto;
import com.reviewer.reviewer.models.User;
import com.reviewer.reviewer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User isInDatabase(UserResponseDto userDto){

        var user = repository.findById(userDto.id());
        if(user.isEmpty()){
            var userAdded = new User(userDto);
            repository.save(userAdded);
            return userAdded;
        }
        return user.get();
    }


//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        return repository.findByEmail(email);
//    }


//    public List<UserResponseDto> findAll(){
//        var users = repository.findAll();
//        List<UserResponseDto> userDto = new ArrayList<>();
//        for (User user : users) {
//            var userResponse = new UserResponseDto(user);
//            userDto.add(userResponse);
//        }
//
//        return userDto;
//    }

//    public UserResponseDto findMe(Principal principal) {
//        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
//        return new UserResponseDto(user);
//    }

}