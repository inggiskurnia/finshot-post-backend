package com.postit.postit.infrastructure.user.dto;

import com.postit.postit.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequestDTO {

    @Max(255)
    private String name;

    @Email
    @Max(255)
    private String email;

    @Size(min = 8)
    private String password;

    public User toEntity(){
        User user = new User();

        user.setName(this.name);
        user.setEmail(this.email);
        user.setPassword(this.password);

        return user;
    }
}
