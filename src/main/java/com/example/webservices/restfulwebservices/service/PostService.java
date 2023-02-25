package com.example.webservices.restfulwebservices.service;

import com.example.webservices.restfulwebservices.model.User;
import com.example.webservices.restfulwebservices.model.Post;
import com.example.webservices.restfulwebservices.persistence.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public List<Post> findPostsByUserId(UUID personId) {
        return postRepository.findAllByUserId(personId);
    }

    public Post createPost(UUID userId, Post post) {
        User user = userService.getUserById(userId);
        post.setUser(user);
        return postRepository.save(post);
    }
}
