package com.example.webservices.restfulwebservices.presentation;

import com.example.webservices.restfulwebservices.dto.UserMapper;
import com.example.webservices.restfulwebservices.dto.UserRequestDto;
import com.example.webservices.restfulwebservices.dto.UserResponseDto;
import com.example.webservices.restfulwebservices.model.User;
import com.example.webservices.restfulwebservices.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    private final MessageSource messageSource;

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers().stream().map(userMapper::toUserResponseDto).toList();
    }

    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable(name = "id") UUID userId) {
        return userMapper.toUserResponseDto(userService.getUserById(userId));
    }

    @PostMapping
    public ResponseEntity<Void> createNewUser(@Valid @RequestBody UserRequestDto userDto) {
        User user = userService.createNewUser(userMapper.fromUserRequestDto(userDto));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable(name = "id") UUID userId) {
        userService.deleteUserById(userId);
    }

    @GetMapping("/i18n")
    public String helloWorld() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, locale);
    }

}
