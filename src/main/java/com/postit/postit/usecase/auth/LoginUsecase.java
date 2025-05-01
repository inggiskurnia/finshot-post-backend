package com.postit.postit.usecase.auth;

import com.postit.postit.infrastructure.user.dto.LoginRequestDTO;
import com.postit.postit.infrastructure.user.dto.LoginResponseDTO;

public interface LoginUsecase {
    LoginResponseDTO authenticateUser(LoginRequestDTO req);
}
