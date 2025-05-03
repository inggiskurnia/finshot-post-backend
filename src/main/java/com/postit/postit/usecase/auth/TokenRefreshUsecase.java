package com.postit.postit.usecase.auth;

import com.postit.postit.infrastructure.auth.dto.LoginResponseDTO;
import com.postit.postit.infrastructure.auth.dto.RefreshTokenRequestDTO;

public interface TokenRefreshUsecase {
    LoginResponseDTO tokenRefresh(RefreshTokenRequestDTO req);
}
