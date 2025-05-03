package com.postit.postit.usecase.post.impl;

import com.postit.postit.common.exceptions.DataNotFoundException;
import com.postit.postit.common.utils.SlugDecoder;
import com.postit.postit.entity.Post;
import com.postit.postit.infrastructure.post.repository.PostRepository;
import com.postit.postit.infrastructure.security.Claims;
import com.postit.postit.usecase.post.DeletePostUsecase;
import org.springframework.stereotype.Service;

@Service
public class DeletePostUsecaseImpl implements DeletePostUsecase {

    private final PostRepository postRepository;

    public DeletePostUsecaseImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void deletePostBySlug(String slug) {

        Long userId = Claims.getUserIdFromJwt();
        String title = SlugDecoder.decode(slug);

        Post post = postRepository.findByAuthorIdAndTitleIgnoreCase(userId, title).orElseThrow(()-> new DataNotFoundException("Post not found !"));
        postRepository.delete(post);
    }
}
