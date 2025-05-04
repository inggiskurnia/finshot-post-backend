package com.postit.postit.infrastructure.post.dto;

import com.postit.postit.entity.Post;
import com.postit.postit.entity.User;
import com.postit.postit.infrastructure.user.dto.UserDetailResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDTO {

    private Long postId;
    private String title;
    private String body;
    private Integer totalViews;
    private String author;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public PostResponseDTO(Post post, User author) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.body = post.getBody();
        this.totalViews = post.getTotalViews();
        this.author = author.getName();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
    }
}
