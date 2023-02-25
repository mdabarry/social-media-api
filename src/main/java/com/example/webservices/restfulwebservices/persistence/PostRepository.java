package com.example.webservices.restfulwebservices.persistence;


import com.example.webservices.restfulwebservices.model.Post;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends ListCrudRepository<Post, UUID> {
    List<Post> findAllByUserId(UUID userId);
}
