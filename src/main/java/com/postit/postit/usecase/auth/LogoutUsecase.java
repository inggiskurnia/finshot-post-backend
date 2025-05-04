package com.postit.postit.usecase.auth;

import com.postit.postit.infrastructure.auth.dto.LogoutRequestDTO;

public interface LogoutUsecase {
    void logoutUser(LogoutRequestDTO req);
}
