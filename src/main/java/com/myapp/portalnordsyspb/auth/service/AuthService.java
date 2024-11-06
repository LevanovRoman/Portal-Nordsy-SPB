package com.myapp.portalnordsyspb.auth.service;

import com.myapp.portalnordsyspb.auth.entity.User;
import com.myapp.portalnordsyspb.auth.entity.UserRole;
import com.myapp.portalnordsyspb.auth.repository.UserRepository;
import com.myapp.portalnordsyspb.auth.utils.AuthResponse;
import com.myapp.portalnordsyspb.auth.utils.LoginRequest;
import com.myapp.portalnordsyspb.auth.utils.RegisterRequest;
import com.myapp.portalnordsyspb.exceptions.CustomUserNotFoundException;
import com.myapp.portalnordsyspb.exceptions.EmailAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest registerRequest) {

        if (userRepository.existsByEmail(registerRequest.getEmail())){
            throw new EmailAlreadyExistException("Такая почта уже существует!");
        }
        var user = User.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(UserRole.USER)
                .build();

        User savedUser = userRepository.save(user);
        var accessToken = jwtService.generateToken(savedUser);
        var refreshToken = refreshTokenService.createRefreshToken(savedUser.getEmail());

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getRefreshToken())
                .build();
    }

    public AuthResponse login(LoginRequest loginRequest) {

        var user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new CustomUserNotFoundException("Пользователь не найден!"));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

//        var user = userRepository.findByEmail(loginRequest.getEmail())
//                .orElseThrow(() -> new CustomUserNotFoundException("Пользователь не найден!"));
        var accessToken = jwtService.generateToken(user);
        var refreshToken = refreshTokenService.createRefreshToken(loginRequest.getEmail());

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getRefreshToken())
                .build();
    }
}
