package com.postit.postit.usecase.post.impl;

import com.postit.postit.common.exceptions.DataNotFoundException;
import com.postit.postit.common.utils.SlugDecoder;
import com.postit.postit.entity.Post;
import com.postit.postit.entity.User;
import com.postit.postit.infrastructure.post.dto.PostRequestDTO;
import com.postit.postit.infrastructure.post.dto.PostResponseDTO;
import com.postit.postit.infrastructure.post.repository.PostRepository;
import com.postit.postit.infrastructure.security.Claims;
import com.postit.postit.infrastructure.user.repository.UserRepository;
import com.postit.postit.usecase.post.UpdatePostUsecase;
import org.springframework.stereotype.Service;

@Service
public class UpdatePostUsecaseImpl implements UpdatePostUsecase {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public UpdatePostUsecaseImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PostResponseDTO updatePost(String slug, PostRequestDTO req) {

        Long userId = Claims.getUserIdFromJwt();
        User user = userRepository.findById(userId).orElseThrow(()-> new DataNotFoundException("User not found !"));
        String title = SlugDecoder.decode(slug);

        Post existingPost = postRepository.findByAuthorIdAndTitleIgnoreCase(userId, title).orElseThrow(()-> new DataNotFoundException("Post not found !"));

        existingPost.setTitle(req.getTitle());
        existingPost.setBody(req.getBody());

        postRepository.save(existingPost);

        return new PostResponseDTO(existingPost, user);
    }

    @Override
    public PostResponseDTO updateTotalView(String slug) {

        Long userId = Claims.getUserIdFromJwt();
        User user = userRepository.findById(userId).orElseThrow(()-> new DataNotFoundException("User not found !"));
        String title = SlugDecoder.decode(slug);

        Post existingPost = postRepository.findByTitleIgnoreCase(title).orElseThrow(()-> new DataNotFoundException("Post not found !"));

        existingPost.setTotalViews(existingPost.getTotalViews() + 1);
        postRepository.save(existingPost);

        return new PostResponseDTO(existingPost, user);
    }
}
