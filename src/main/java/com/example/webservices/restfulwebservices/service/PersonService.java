package com.example.webservices.restfulwebservices.service;

import com.example.webservices.restfulwebservices.exception.PersonNotFoundException;
import com.example.webservices.restfulwebservices.model.Person;
import com.example.webservices.restfulwebservices.persistence.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    public Person getPersonById(UUID personId) {
        return personRepository.findById(personId).orElseThrow(() -> new PersonNotFoundException(personId));
    }

    public Person createNewPerson(Person person) {
        return personRepository.save(person);
    }

    public void deletePersonById(UUID personId) {
        personRepository.deleteById(personId);
    }
}

