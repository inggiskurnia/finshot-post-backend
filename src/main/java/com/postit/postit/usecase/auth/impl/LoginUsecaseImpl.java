package com.postit.postit.usecase.auth.impl;

import com.postit.postit.common.exceptions.DataNotFoundException;
import com.postit.postit.infrastructure.security.TokenService;
import com.postit.postit.infrastructure.user.dto.LoginRequestDTO;
import com.postit.postit.infrastructure.user.dto.LoginResponseDTO;
import com.postit.postit.usecase.auth.LoginUseCase;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class LoginUsecaseImpl implements LoginUseCase {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public LoginUsecaseImpl(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @Override
    public LoginResponseDTO authenticateUser(LoginRequestDTO req) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
            String token = tokenService.generateToken(authentication);
            return new LoginResponseDTO(req.getEmail(), token);
        }
        catch (AuthenticationException e){
            throw new DataNotFoundException("Wrong credentials");
        }
    }
}
