package com.postit.postit.usecase.auth.impl;

import com.postit.postit.common.exceptions.DataNotFoundException;
import com.postit.postit.entity.User;
import com.postit.postit.infrastructure.user.repository.UserRepository;
import com.postit.postit.usecase.auth.TokenGeneratorUsecase;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenGeneratorUsecaseImpl implements TokenGeneratorUsecase {

    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;

    public TokenGeneratorUsecaseImpl(JwtEncoder jwtEncoder, UserRepository userRepository) {
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public String generateAccessToken(Authentication authentication) {
        Instant now = Instant.now();
        long expiry = 600L;

        String email = authentication.getName();
        User user = userRepository.findByEmailIgnoreCase(email).orElseThrow(()-> new DataNotFoundException("User with email " + email + " not found !"));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(email)
                .claim("userId", user.getId())
                .claim("tokenType", "access")
                .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    @Override
    public String generateRefreshToken(Authentication authentication) {
        Instant now = Instant.now();
        long expiry = 86400L;

        String email = authentication.getName();
        User user = userRepository.findByEmailIgnoreCase(email).orElseThrow(()-> new DataNotFoundException("User with email " + email + " not found !"));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(email)
                .claim("userId", user.getId())
                .claim("tokenType", "refresh")
                .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
