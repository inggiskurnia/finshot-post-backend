package com.postit.postit.usecase.user;

import com.postit.postit.infrastructure.user.dto.CreateUserRequestDTO;
import com.postit.postit.infrastructure.user.dto.UserDetailResponseDTO;

public interface CreateUserUsecase {

    UserDetailResponseDTO createUser(CreateUserRequestDTO req);
}
