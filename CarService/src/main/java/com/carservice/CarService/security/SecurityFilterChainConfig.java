package com.carservice.CarService.security;

import com.carservice.CarService.jwt.JWTAuthenticationEntryPoint;
import com.carservice.CarService.jwt.JWTAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityFilterChainConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    private final JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors
                        .configurationSource(request -> {
                            CorsConfiguration corsConfig = new CorsConfiguration();
                            corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
                            corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                            corsConfig.setAllowedHeaders(Arrays.asList("*"));
                            corsConfig.setExposedHeaders(Arrays.asList("Authorization", "Content-Type"));
                            corsConfig.setMaxAge(3600L);
                            return corsConfig;
                        })
                )
//                .authorizeHttpRequests((authz) -> authz
//                        .requestMatchers(
//                                "/api/v1/commissions/**"
//                        )
//                        .hasAnyRole("MANAGER", "RECEPTIONIST", "CONTRACTOR")
//                        .requestMatchers(
//                                "/api/v1/commissions/**",
//                                "/api/v1/clients/**",
//                                "/api/v1/vehicles/**",
//                                "/api/v1/invoices/**"
//                        )
//                        .hasAnyRole("MANAGER", "RECEPTIONIST")
//                    .requestMatchers(
//                        HttpMethod.POST,
//                        "/api/v1/auth/login"
//                    )
//                        .permitAll()
//                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint));
        return http.build();
    }
}