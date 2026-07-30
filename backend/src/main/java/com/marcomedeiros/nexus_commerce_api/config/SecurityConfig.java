package com.marcomedeiros.nexus_commerce_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desabilita proteção contra CSRF para o H2 funcionar
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) // Permite que o H2 abra dentro do navegador
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // Libera o caminho do console
                        .anyRequest().permitAll() // Libera o resto da API por enquanto
                );
        return http.build();
    }
}