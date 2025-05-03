package com.postit.postit.usecase.post.impl;

import com.postit.postit.common.exceptions.DataNotFoundException;
import com.postit.postit.common.utils.PaginationInfo;
import com.postit.postit.common.utils.SlugDecoder;
import com.postit.postit.entity.Post;
import com.postit.postit.infrastructure.post.dto.PaginatedPostRequestDTO;
import com.postit.postit.infrastructure.post.dto.PostResponseDTO;
import com.postit.postit.infrastructure.post.repository.PostRepository;
import com.postit.postit.infrastructure.security.Claims;
import com.postit.postit.usecase.post.GetPostUsecase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class GetPostUsecaseImpl implements GetPostUsecase {

    private final PostRepository postRepository;

    public GetPostUsecaseImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostResponseDTO getPostBySlug(String slug) {

        String postTitle = SlugDecoder.decode(slug);
        Post post = postRepository.findByTitleIgnoreCase(postTitle).orElseThrow(()-> new DataNotFoundException("Post not found !"));

        return new PostResponseDTO(post, post.getAuthor());
    }

    @Override
    public PaginationInfo<PostResponseDTO> getPaginatedAllPost(PaginatedPostRequestDTO req) {
        PageRequest pageRequest = PageRequest.of(req.getPage(), req.getLimit());

        Page<PostResponseDTO> postResponseDTOS = postRepository.findPaginatedAllPost(pageRequest);

        return new PaginationInfo<>(postResponseDTOS, postResponseDTOS.getContent());
    }

    @Override
    public PaginationInfo<PostResponseDTO> getPaginatedByUserId(PaginatedPostRequestDTO req) {

        Long userId = Claims.getUserIdFromJwt();
        PageRequest pageRequest = PageRequest.of(req.getPage(), req.getLimit());

        Page<PostResponseDTO> postResponseDTOS = postRepository.findPaginatedByUserId(userId,pageRequest);

        return new PaginationInfo<>(postResponseDTOS, postResponseDTOS.getContent());
    }
}
