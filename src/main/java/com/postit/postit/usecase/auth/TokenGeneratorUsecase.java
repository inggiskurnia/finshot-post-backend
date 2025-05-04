package com.postit.postit.usecase.auth;

import org.springframework.security.core.Authentication;

public interface TokenGeneratorUsecase {
    String generateAccessToken(Authentication authentication);
    String generateRefreshToken(Authentication authentication);
}
