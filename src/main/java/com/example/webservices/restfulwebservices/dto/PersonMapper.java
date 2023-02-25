package com.example.webservices.restfulwebservices.dto;

import com.example.webservices.restfulwebservices.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public Person fromPersonRequestDto(PersonRequestDto personRequestDto) {
        return Person.builder().name(personRequestDto.name()).birthDate(personRequestDto.birthDate()).build();
    }

    public PersonResponseDto toPersonResponseDto(Person person) {
        return new PersonResponseDto(person.getId(), person.getName(), person.getBirthDate());
    }
}
