package com.postit.postit.usecase.auth;

import com.postit.postit.infrastructure.auth.dto.LoginResponseDTO;

public interface TokenRefreshUsecase {
    LoginResponseDTO tokenRefresh(String refreshToken);
}
