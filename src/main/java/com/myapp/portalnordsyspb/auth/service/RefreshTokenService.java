package com.myapp.portalnordsyspb.auth.service;

import com.myapp.portalnordsyspb.auth.entity.RefreshToken;
import com.myapp.portalnordsyspb.auth.entity.User;
import com.myapp.portalnordsyspb.auth.repository.RefreshTokenRepository;
import com.myapp.portalnordsyspb.auth.repository.UserRepository;
import com.myapp.portalnordsyspb.exceptions.ObjectNotFoundException;
import com.myapp.portalnordsyspb.exceptions.RefreshTokenExpiredException;
import com.myapp.portalnordsyspb.exceptions.RefreshTokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final UserRepository userRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${spring.app.jwtRefreshExpirationMs}")
    private long jwtRefreshExpirationMs;

    public RefreshToken createRefreshToken(String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new ObjectNotFoundException("User with mail : " + username + " not found."));

        RefreshToken refreshToken = user.getRefreshToken();

        if (refreshToken == null) {
            refreshToken = RefreshToken.builder()
                    .refreshToken(UUID.randomUUID().toString())
                    .expirationTime(Instant.now().plusMillis(jwtRefreshExpirationMs))
                    .user(user)
                    .build();

            refreshTokenRepository.save(refreshToken);
        }

        return refreshToken;
    }

    public RefreshToken verifyRefreshToken(String refreshToken) {
        RefreshToken refToken = refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new RefreshTokenNotFoundException("Refresh token not found!"));

        if (refToken.getExpirationTime().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refToken);
            throw new RefreshTokenExpiredException("Refresh Token expired!");
        }

        return refToken;
    }
}
