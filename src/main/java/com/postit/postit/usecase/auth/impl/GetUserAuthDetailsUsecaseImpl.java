package com.postit.postit.usecase.auth.impl;

import com.postit.postit.common.exceptions.DataNotFoundException;
import com.postit.postit.entity.User;
import com.postit.postit.infrastructure.auth.dto.UserAuth;
import com.postit.postit.infrastructure.user.repository.UserRepository;
import com.postit.postit.usecase.auth.GetUserAuthDetailsUsecase;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GetUserAuthDetailsUsecaseImpl implements GetUserAuthDetailsUsecase {

    private final UserRepository userRepository;

    public GetUserAuthDetailsUsecaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User existingUser = userRepository.findByEmailContainsIgnoreCase(username)
                .orElseThrow(()-> new DataNotFoundException("Username with email "+ username + " not found !"));

        UserAuth userAuth = new UserAuth();
        userAuth.setUser(existingUser);
        return userAuth;
    }
}
