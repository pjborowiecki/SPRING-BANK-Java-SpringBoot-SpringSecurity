package com.pjborowiecki.springbank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {

        private static final String[] PUBLIC_URLS = {
                        "/api/v1/contact", "/api/v1/notifications", "/api/v1/auth/signup"
        };

        private static final String[] PROTECTED_URLS = {
                        "/api/v1/account", "/api/v1/balance", "/api/v1/loans", "/api/v1/cards"
        };

        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf((csrf) -> csrf.disable())
                                .authorizeHttpRequests((request) -> request
                                                .requestMatchers(PROTECTED_URLS).authenticated()
                                                .requestMatchers(PUBLIC_URLS).permitAll())
                                .formLogin(Customizer.withDefaults())
                                .httpBasic(Customizer.withDefaults());
                return http.build();
        }

        @Bean
        PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

}
