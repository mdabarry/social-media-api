package com.example.webservices.restfulwebservices.presentation;

import com.example.webservices.restfulwebservices.dto.PersonMapper;
import com.example.webservices.restfulwebservices.dto.PersonRequestDto;
import com.example.webservices.restfulwebservices.dto.PersonResponseDto;
import com.example.webservices.restfulwebservices.model.Person;
import com.example.webservices.restfulwebservices.service.PersonService;
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
@RequestMapping("/people")
public class PersonController {
    private final PersonService personService;
    private final PersonMapper personMapper;

    private final MessageSource messageSource;

    @GetMapping
    public List<PersonResponseDto> getAllPeople() {
        return personService.getAllPeople().stream().map(personMapper::toPersonResponseDto).toList();
    }

    @GetMapping("/{id}")
    public PersonResponseDto getPerson(@PathVariable(name = "id") UUID personId) {
        return personMapper.toPersonResponseDto(personService.getPersonById(personId));
    }

    @PostMapping
    public ResponseEntity<Void> createNewPerson(@Valid @RequestBody PersonRequestDto personDto) {
        Person person = personService.createNewPerson(personMapper.fromPersonRequestDto(personDto));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(person.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable(name = "id") UUID personId) {
        personService.deletePersonById(personId);
    }

    @GetMapping("/i18n")
    public String helloWorld() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, locale);
    }

}
