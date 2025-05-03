package com.postit.postit.usecase.post;

import com.postit.postit.infrastructure.post.dto.PostRequestDTO;
import com.postit.postit.infrastructure.post.dto.PostResponseDTO;

public interface UpdatePostUsecase {

    PostResponseDTO updatePost(String slug, PostRequestDTO req);
    PostResponseDTO updateTotalView(String slug);
}
