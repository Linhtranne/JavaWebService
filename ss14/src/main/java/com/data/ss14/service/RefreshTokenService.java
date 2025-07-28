package com.data.ss14.service;

import com.data.ss14.model.entity.RefreshToken;
import com.data.ss14.model.entity.User;

import java.util.Optional;

public interface RefreshTokenService{
    RefreshToken createRefreshToken(User user, String ip);
    boolean isValid(RefreshToken token, String ip);
    void deleteByUser(User user);
    Optional<RefreshToken> findByToken(String token);

    void manageRefreshTokenLimit(User user, String ip);
}
