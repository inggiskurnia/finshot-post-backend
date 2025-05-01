package com.postit.postit.usecase.auth.impl;

import com.postit.postit.infrastructure.security.Claims;
import com.postit.postit.usecase.auth.LogoutUsecase;
import com.postit.postit.usecase.auth.TokenBlacklistUsecase;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

@Service
public class LogoutUsecaseImpl implements LogoutUsecase {

    private final JwtDecoder jwtDecoder;
    private final TokenBlacklistUsecase tokenBlacklistUsecase;

    public LogoutUsecaseImpl(JwtDecoder jwtDecoder, TokenBlacklistUsecase tokenBlacklistUsecase) {
        this.jwtDecoder = jwtDecoder;
        this.tokenBlacklistUsecase = tokenBlacklistUsecase;
    }

    @Override
    public void logoutUser(String refreshToken) {
        String accessToken = Claims.getJwtTokenString();
        String accessTokenExpiryDate = Claims.getExpirationDateFromJwt();

        Jwt refreshTokenJwt = jwtDecoder.decode(refreshToken);

        tokenBlacklistUsecase.blacklistToken(accessToken, accessTokenExpiryDate);

        assert refreshTokenJwt.getExpiresAt() != null;
        tokenBlacklistUsecase.blacklistToken(refreshToken, refreshTokenJwt.getExpiresAt().toString());

    }
}
