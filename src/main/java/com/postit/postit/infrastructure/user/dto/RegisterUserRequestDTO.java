package com.postit.postit.infrastructure.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequestDTO {

    @Max(255)
    private String name;

    @Email
    @Max(255)
    private String email;

    private String password;
}
