package com.example.webservices.restfulwebservices.presentation;

import com.example.webservices.restfulwebservices.dto.user.UserMapper;
import com.example.webservices.restfulwebservices.dto.user.UserRequestDto;
import com.example.webservices.restfulwebservices.dto.user.UserResponseDto;
import com.example.webservices.restfulwebservices.model.User;
import com.example.webservices.restfulwebservices.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class PersonController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers().stream().map(userMapper::toUserResponseDto).toList();
    }

    @GetMapping("/{id}")
    public UserResponseDto getPerson(@PathVariable(name = "id") UUID userId) {
        return userMapper.toUserResponseDto(userService.getUserById(userId));
    }

    @PostMapping
    public ResponseEntity<Void> createNewUser(@Valid @RequestBody UserRequestDto personDto) {
        User user = userService.createNewUser(userMapper.fromPersonRequestDto(personDto));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable(name = "id") UUID personId) {
        userService.deleteUserById(personId);
    }

}
