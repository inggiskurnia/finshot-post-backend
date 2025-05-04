package com.postit.postit.usecase.auth.impl;

import com.postit.postit.common.exceptions.DataNotFoundException;
import com.postit.postit.common.exceptions.JwtParsingException;
import com.postit.postit.entity.User;
import com.postit.postit.infrastructure.auth.dto.LoginResponseDTO;
import com.postit.postit.infrastructure.auth.dto.RefreshTokenRequestDTO;
import com.postit.postit.infrastructure.user.repository.UserRepository;
import com.postit.postit.usecase.auth.TokenRefreshUsecase;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenRefreshUseCaseImpl implements TokenRefreshUsecase {

    private final JwtDecoder jwtDecoder;
    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;

    public TokenRefreshUseCaseImpl(JwtDecoder jwtDecoder, JwtEncoder jwtEncoder, UserRepository userRepository) {
        this.jwtDecoder = jwtDecoder;
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public LoginResponseDTO tokenRefresh(RefreshTokenRequestDTO req) {



        try {
            Instant now = Instant.now();
            long expiry = 600L;

            Jwt decodedJwt = jwtDecoder.decode(req.getRefreshToken());

            String tokenType = decodedJwt.getClaimAsString("tokenType");
            if (tokenType == null || !tokenType.equals("refresh")){
                throw new JwtException("Invalid token type");
            }

            String email = decodedJwt.getSubject();
            User user = userRepository.findByEmailIgnoreCase(email).orElseThrow(()-> new DataNotFoundException("User with email " + email + " not found !"));

            JwtClaimsSet claims = JwtClaimsSet.builder()
                    .issuer("self")
                    .issuedAt(now)
                    .expiresAt(now.plusSeconds(expiry))
                    .subject(email)
                    .claim("userId", user.getId())
                    .build();

            String newAccessToken = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

            return new LoginResponseDTO(email, newAccessToken, req.getRefreshToken());

        }
        catch (JwtException e){
            throw new JwtParsingException("Invalid refresh token");
        }
    }
}
