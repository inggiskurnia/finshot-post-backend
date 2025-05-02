package com.postit.postit.infrastructure.auth.controller;

import com.postit.postit.common.response.ApiResponse;
import com.postit.postit.infrastructure.auth.dto.LoginRequestDTO;
import com.postit.postit.infrastructure.auth.dto.LogoutRequestDTO;
import com.postit.postit.usecase.auth.LoginUsecase;
import com.postit.postit.usecase.auth.LogoutUsecase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final LoginUsecase loginUseCase;
    private final LogoutUsecase logoutUsecase;

    public AuthController(LoginUsecase loginUseCase, LogoutUsecase logoutUsecase) {
        this.loginUseCase = loginUseCase;
        this.logoutUsecase = logoutUsecase;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDTO req){
        return ApiResponse.successResponse(HttpStatus.OK.value(), "Login successfully !", loginUseCase.authenticateUser(req));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(@RequestBody LogoutRequestDTO req){
        logoutUsecase.logoutUser(req);
        return ApiResponse.successResponse(HttpStatus.OK.value(), "Logout success", null);
    }
}
