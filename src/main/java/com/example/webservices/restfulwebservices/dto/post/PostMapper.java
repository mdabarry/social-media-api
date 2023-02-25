package com.example.webservices.restfulwebservices.dto.post;

import com.example.webservices.restfulwebservices.model.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    public PostResponseDto toPostResponseDto(Post post) {
        return new PostResponseDto(post.getId(), post.getCreatedAt(), post.getTitle(), post.getContent());
    }

    public Post fromPostRequestDto(PostRequestDto postDto) {
        return Post.builder().createdAt(postDto.createdAt()).title(postDto.title()).content(postDto.content()).build();
    }
}
