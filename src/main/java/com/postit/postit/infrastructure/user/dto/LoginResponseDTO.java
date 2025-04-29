package com.postit.postit.infrastructure.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

    private String email;
    private String accessToken;
    private String refreshToken;
}
