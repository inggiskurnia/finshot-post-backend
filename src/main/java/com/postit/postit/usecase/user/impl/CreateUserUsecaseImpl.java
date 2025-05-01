package com.postit.postit.usecase.user.impl;

import com.postit.postit.entity.User;
import com.postit.postit.infrastructure.user.dto.CreateUserRequestDTO;
import com.postit.postit.infrastructure.user.dto.UserDetailResponseDTO;
import com.postit.postit.infrastructure.user.repository.UserRepository;
import com.postit.postit.usecase.user.CreateUserUsecase;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUsecaseImpl implements CreateUserUsecase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateUserUsecaseImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetailResponseDTO createUser(CreateUserRequestDTO req) {

        User newUser = req.toEntity();
        newUser.setPassword(passwordEncoder.encode(req.getPassword()));

        User savedUser = userRepository.save(newUser);

        return new UserDetailResponseDTO(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
    }
}
