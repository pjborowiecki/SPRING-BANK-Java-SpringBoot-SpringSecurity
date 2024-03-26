package com.pjborowiecki.springbank.security;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {

        private static final String[] PUBLIC_ROUTES = {
                        "/api/v1/auth/signup",
                        "/api/v1/auth/signin",
                        "/api/v1/auth/current-user",
                        "/api/v1/messages",
                        "/api/v1/notifications"
        };

        private static final String[] PROTECTED_ROUTES = {
                        "/api/v1/accounts",
                        "/api/v1/transactions",
                        "/api/v1/loans",
                        "/api/v1/cards",
                        "/api/v1/customers"
        };

        private static final String[] ADMIN_ONLY_ROUTES = {
                        "/api/v1/dashboard"
        };

        private CorsConfiguration corsHandler() {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setMaxAge(3600L);
                return config;
        }

        private CsrfTokenRequestAttributeHandler csrfHandler() {
                CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
                requestHandler.setCsrfRequestAttributeName("_csrf");
                return requestHandler;
        }

        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .cors(corsCustomizer -> corsCustomizer
                                                .configurationSource(request -> corsHandler()))
                                .csrf((csrf) -> csrf.csrfTokenRequestHandler(csrfHandler())
                                                .ignoringRequestMatchers(PUBLIC_ROUTES)
                                                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                                .addFilterAfter(new SecurityCsrfCookieFilter(),
                                                BasicAuthenticationFilter.class)
                                .authorizeHttpRequests((request) -> request
                                                .requestMatchers(ADMIN_ONLY_ROUTES).hasRole("ADMIN")
                                                .requestMatchers(PROTECTED_ROUTES).hasAnyRole("USER", "ADMIN")
                                                .requestMatchers(PROTECTED_ROUTES).authenticated()
                                                .requestMatchers(PUBLIC_ROUTES).permitAll())
                                .formLogin(Customizer.withDefaults())
                                .httpBasic(Customizer.withDefaults());
                return http.build();
        }

        @Bean
        PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

}
