package com.pjborowiecki.springbank.config;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {

        private static final String[] PUBLIC_URLS = {
                        "/api/v1/auth/signup",
                        "/api/v1/auth/signin",
                        "/api/v1/auth/current-user",
                        "/api/v1/messages",
                        "/api/v1/notifications"
        };

        private static final String[] PROTECTED_URLS = {
                        "/api/v1/accounts",
                        "/api/v1/transactions",
                        "/api/v1/loans",
                        "/api/v1/cards",
                        "/api/v1/customers"
        };

        private CorsConfiguration buildCorsConfiguration() {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setMaxAge(3600L);
                return config;
        }

        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .cors(corsCustomizer -> corsCustomizer
                                                .configurationSource(request -> buildCorsConfiguration()))
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
