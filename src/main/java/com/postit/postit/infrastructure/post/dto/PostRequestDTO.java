package com.postit.postit.infrastructure.post.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDTO {

    @NotNull
    @Size(max = 100)
    private String title;

    @NotNull
    @Size(min = 10)
    private String body;
}
