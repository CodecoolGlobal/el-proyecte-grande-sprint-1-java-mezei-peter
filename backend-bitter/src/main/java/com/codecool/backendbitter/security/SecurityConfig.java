package com.codecool.backendbitter.security;

import com.codecool.backendbitter.service.CustomUserDetailsService;
import com.codecool.backendbitter.service.jwt.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtGenerator jwtGenerator,
                                                   AuthenticationManager authenticationManager) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .cors();
        http.addFilter(new CustomUsernamePasswordAuthenticationFilter(jwtGenerator, authenticationManager));
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring().requestMatchers("/css/**","/js/**","/images/**"));
    }

    @Bean
    public AuthenticationManager authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
