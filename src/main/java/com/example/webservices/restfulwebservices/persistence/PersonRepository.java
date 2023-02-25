package com.example.webservices.restfulwebservices.persistence;


import com.example.webservices.restfulwebservices.model.Person;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface PersonRepository extends ListCrudRepository<Person, UUID> {}
