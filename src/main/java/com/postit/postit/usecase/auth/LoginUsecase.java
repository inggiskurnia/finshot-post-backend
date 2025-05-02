package com.postit.postit.usecase.auth;

import com.postit.postit.infrastructure.auth.dto.LoginRequestDTO;
import com.postit.postit.infrastructure.auth.dto.LoginResponseDTO;

public interface LoginUsecase {
    LoginResponseDTO authenticateUser(LoginRequestDTO req);
}
