package com.postit.postit.infrastructure.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedPostRequestDTO {

    private int page;
    private int limit;
}
