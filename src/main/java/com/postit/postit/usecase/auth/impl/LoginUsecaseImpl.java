package com.postit.postit.usecase.auth.impl;

import com.postit.postit.common.exceptions.DataNotFoundException;
import com.postit.postit.infrastructure.user.dto.LoginRequestDTO;
import com.postit.postit.infrastructure.user.dto.LoginResponseDTO;
import com.postit.postit.usecase.auth.LoginUsecase;
import com.postit.postit.usecase.auth.TokenGeneratorUsecase;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class LoginUsecaseImpl implements LoginUsecase {

    private final AuthenticationManager authenticationManager;
    private final TokenGeneratorUsecase tokenGeneratorUsecase;

    public LoginUsecaseImpl(AuthenticationManager authenticationManager, TokenGeneratorUsecase tokenGeneratorUsecase) {
        this.authenticationManager = authenticationManager;
        this.tokenGeneratorUsecase = tokenGeneratorUsecase;
    }

    @Override
    public LoginResponseDTO authenticateUser(LoginRequestDTO req) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
            String accessToken = tokenGeneratorUsecase.generateAccessToken(authentication);
            String refreshToken = tokenGeneratorUsecase.generateRefreshToken(authentication);

            return new LoginResponseDTO(req.getEmail(), accessToken, refreshToken);
        }
        catch (AuthenticationException e){
            throw new DataNotFoundException("Wrong credentials");
        }
    }
}
