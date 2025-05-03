package com.postit.postit.infrastructure.user.dto;

import com.postit.postit.entity.User;
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

    public UserDetailResponseDTO(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
    }
}
