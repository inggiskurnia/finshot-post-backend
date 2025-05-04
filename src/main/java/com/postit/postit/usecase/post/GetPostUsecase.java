package com.postit.postit.usecase.post;

import com.postit.postit.common.utils.PaginationInfo;
import com.postit.postit.infrastructure.post.dto.PaginatedPostRequestDTO;
import com.postit.postit.infrastructure.post.dto.PostResponseDTO;

import java.util.List;

public interface GetPostUsecase {
    PostResponseDTO getPostBySlug(String slug);
    PaginationInfo<PostResponseDTO> getPaginatedAllPost(PaginatedPostRequestDTO req);
    PaginationInfo<PostResponseDTO> getPaginatedByUserId(PaginatedPostRequestDTO req);
}
