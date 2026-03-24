package com.example.demo.auth.security;

import com.example.demo.auth.service.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/projects/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/projects/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/projects/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.GET, "/api/projects/**").authenticated()

                        .requestMatchers(HttpMethod.GET, "/api/tasks/my").hasAnyRole("USER", "MANAGER")
                        .requestMatchers(HttpMethod.GET, "/api/tasks/user/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.GET, "/api/tasks/project/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "/api/tasks/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/tasks/**").hasRole("MANAGER")

                        .requestMatchers("/api/users/**").hasRole("MANAGER")

                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}