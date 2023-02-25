package com.example.webservices.restfulwebservices.presentation;

import com.example.webservices.restfulwebservices.dto.post.PostMapper;
import com.example.webservices.restfulwebservices.dto.post.PostRequestDto;
import com.example.webservices.restfulwebservices.dto.post.PostResponseDto;
import com.example.webservices.restfulwebservices.model.Post;
import com.example.webservices.restfulwebservices.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;

    @GetMapping("/{id}/posts")
    public List<PostResponseDto> getAllUserPosts(@PathVariable(name = "id") UUID userId) {
        List<Post> posts = postService.findPostsByUserId(userId);
        return posts.stream().map(postMapper::toPostResponseDto).toList();
    }

    @PostMapping("/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewPost(@PathVariable(name = "id") UUID personId, @Valid @RequestBody PostRequestDto postDto) {
        postService.createPost(personId, postMapper.fromPostRequestDto(postDto));
    }

}
