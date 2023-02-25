package com.example.webservices.restfulwebservices.dto;

import com.example.webservices.restfulwebservices.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User fromUserRequestDto(UserRequestDto userCreateRequestDto) {
        return User.builder().name(userCreateRequestDto.name()).birthDate(userCreateRequestDto.birthDate()).build();
    }

    public UserResponseDto toUserResponseDto(User user) {
        return new UserResponseDto(user.getId(), user.getName(), user.getBirthDate());
    }
}
