package com.postit.postit.infrastructure.user.controller;

import com.postit.postit.common.response.ApiResponse;
import com.postit.postit.infrastructure.user.dto.CreateUserRequestDTO;
import com.postit.postit.usecase.auth.GetUserAuthDetailsUsecase;
import com.postit.postit.usecase.user.CreateUserUsecase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final CreateUserUsecase createUserUsecase;
    private final GetUserAuthDetailsUsecase getUserAuthDetailsUsecase;

    public UserController(CreateUserUsecase createUserUsecase, GetUserAuthDetailsUsecase getUserAuthDetailsUsecase) {
        this.createUserUsecase = createUserUsecase;
        this.getUserAuthDetailsUsecase = getUserAuthDetailsUsecase;
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequestDTO req){
        return ApiResponse.successResponse(HttpStatus.OK.value(), "Create new use success !", createUserUsecase.createUser(req));
    }


    @GetMapping("/{email}")
    public ResponseEntity<?> getUserDetail(@PathVariable String email){
        return ApiResponse.successResponse(HttpStatus.OK.value(), "Get user detail success !", getUserAuthDetailsUsecase.loadUserByUsername(email));
    }

}
