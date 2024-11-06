package com.myapp.portalnordsyspb.auth.service;

import com.myapp.portalnordsyspb.auth.entity.RefreshToken;
import com.myapp.portalnordsyspb.auth.entity.User;
import com.myapp.portalnordsyspb.auth.repository.RefreshTokenRepository;
import com.myapp.portalnordsyspb.auth.repository.UserRepository;
import com.myapp.portalnordsyspb.exceptions.RefreshTokenExpiredException;
import com.myapp.portalnordsyspb.exceptions.RefreshTokenNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final UserRepository userRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(UserRepository userRepository, RefreshTokenRepository refreshTokenRepository) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public RefreshToken createRefreshToken(String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь с почтой : " + username + " не найден."));

        RefreshToken refreshToken = user.getRefreshToken();

        if (refreshToken == null) {
            long refreshTokenValidity = 5*60*60*10000;
            refreshToken = RefreshToken.builder()
                    .refreshToken(UUID.randomUUID().toString())
                    .expirationTime(Instant.now().plusMillis(refreshTokenValidity))
                    .user(user)
                    .build();

            refreshTokenRepository.save(refreshToken);
        }

        return refreshToken;
    }

    public RefreshToken verifyRefreshToken(String refreshToken) {
        RefreshToken refToken = refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new RefreshTokenNotFoundException("Refresh token не найден!"));

        if (refToken.getExpirationTime().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refToken);
            throw new RefreshTokenExpiredException("Refresh Token срок действия истёк!");
        }

        return refToken;
    }
}
