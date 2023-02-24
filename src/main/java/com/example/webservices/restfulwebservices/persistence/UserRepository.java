package com.example.webservices.restfulwebservices.persistence;


import com.example.webservices.restfulwebservices.model.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface UserRepository extends ListCrudRepository<User, UUID> {}
