package com.utn.simulacro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // Endpoints públicos
                        .requestMatchers("/api/auth/**").permitAll()

                        // USER y ADMIN pueden ver juegos
                        .requestMatchers(HttpMethod.GET, "/api/games/**")
                        .hasAnyRole("USER", "ADMIN")

                        // Solo ADMIN puede modificar juegos
                        .requestMatchers(HttpMethod.POST, "/api/games/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/games/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/games/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/games/**")
                        .hasRole("ADMIN")

                        // Solo ADMIN puede acceder a miembros y reservas
                        .requestMatchers("/api/members/**")
                        .hasRole("ADMIN")
                        .requestMatchers("/api/reservations/**")
                        .hasRole("ADMIN")

                        // Cualquier otro endpoint requiere estar autenticado
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
