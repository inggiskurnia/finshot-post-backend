package com.postit.postit.usecase.post.impl;

import com.postit.postit.common.exceptions.DataNotFoundException;
import com.postit.postit.common.exceptions.DuplicatePostException;
import com.postit.postit.entity.Post;
import com.postit.postit.entity.User;
import com.postit.postit.infrastructure.post.dto.PostRequestDTO;
import com.postit.postit.infrastructure.post.dto.PostResponseDTO;
import com.postit.postit.infrastructure.post.repository.PostRepository;
import com.postit.postit.infrastructure.security.Claims;
import com.postit.postit.infrastructure.user.repository.UserRepository;
import com.postit.postit.usecase.post.CreatePostUsecase;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreatePostUsecaseImpl implements CreatePostUsecase {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CreatePostUsecaseImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PostResponseDTO createNewPost(PostRequestDTO req) {

        Long userId = Claims.getUserIdFromJwt();
        User user = userRepository.findById(userId).orElseThrow(()-> new DataNotFoundException("Email not found !"));

        Optional<Post> duplicatePost = postRepository.findByTitleIgnoreCase(req.getTitle());
        if (duplicatePost.isPresent()){
            throw new DuplicatePostException("Post with this title already exist");
        }

        Post newPost = new Post();
        newPost.setTitle(req.getTitle());
        newPost.setBody(req.getBody());
        newPost.setAuthor(user);
        newPost.setTotalViews(0);

        return new PostResponseDTO(postRepository.save(newPost), user);
    }
}
