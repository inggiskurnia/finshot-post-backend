package com.postit.postit.infrastructure.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailResponseDTO {

    private Long id;
    private String email;
    private String name;
}
