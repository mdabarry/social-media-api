package com.example.webservices.restfulwebservices.dto.user;

import com.example.webservices.restfulwebservices.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User fromPersonRequestDto(UserRequestDto userRequestDto) {
        return User.builder().name(userRequestDto.name()).birthDate(userRequestDto.birthDate()).build();
    }

    public UserResponseDto toUserResponseDto(User user) {
        return new UserResponseDto(user.getId(), user.getName(), user.getBirthDate());
    }
}
