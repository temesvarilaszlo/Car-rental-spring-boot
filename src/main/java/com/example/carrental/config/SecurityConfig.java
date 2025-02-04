package com.example.carrental.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserRoleConfig userRoleConfig;

    public SecurityConfig(UserRoleConfig userRoleConfig) {
        this.userRoleConfig = userRoleConfig;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/cars/free/**").permitAll()

                        .requestMatchers("/admin/**", "/cars/**").access((authentication, context) -> {
                            if (userRoleConfig.isAdmin()) {
                                return new AuthorizationDecision(true);
                            } else {
                                return new AuthorizationDecision(false);
                            }
                        })
                        .anyRequest().permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable);

        return http.build();
    }
}

