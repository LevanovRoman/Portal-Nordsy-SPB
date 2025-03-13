package com.myapp.portalnordsyspb.auth.config;


import com.myapp.portalnordsyspb.auth.service.AuthFilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    private final AuthFilterService authFilterService;
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private static final String[] AUTH_WHITELIST = {
            "/**",
            "/api/**",
            "/actuator/**",
            "/api/v1/auth/**",
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };
    private static final String[] AUTH_WHITELIST_2 = {
            "/api/auth/**",
            "/api/level-5s/all-areas5s",
            "/api/table-pu/week-result/**",
            "/api/table-pu/all-weeks/**",
            "/api/table-pu/all-results",
            "/actuator/**",
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/v1/auth/**", "/forgotPassword/**")
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/user/**").hasAnyRole("ADMIN", "USER")
//                        .requestMatchers("/api/table-5s/list").hasRole("USER")
//                        .requestMatchers("/api/**").permitAll()
                        .requestMatchers(AUTH_WHITELIST).permitAll()
//                        .requestMatchers(HttpMethod.POST, AUTH_WHITELIST).permitAll()
//                        .requestMatchers(HttpMethod.PUT, AUTH_WHITELIST).permitAll()
//                        .requestMatchers(HttpMethod.DELETE, AUTH_WHITELIST).permitAll()
//                        .requestMatchers(HttpMethod.PUT, "/api/table-pu/**").hasRole("USER")
//                        .requestMatchers(HttpMethod.POST, "/api/table-5s/create-month5s").hasRole("USER")
//                        .requestMatchers(HttpMethod.DELETE, "/api/table-pu/**").hasRole("USER")
//                        .requestMatchers(HttpMethod.GET, "/api/counter/**").hasRole("USER")
                        .anyRequest().authenticated())
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authFilterService, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}

