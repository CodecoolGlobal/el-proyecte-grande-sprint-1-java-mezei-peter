package com.codecool.backendbitter.security;

import com.codecool.backendbitter.service.CustomUserDetailsService;
import com.codecool.backendbitter.service.jwt.JwtService;
import io.jsonwebtoken.io.Decoders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtService jwtService,
                                                   AuthenticationManager authenticationManager) throws Exception {
        http.csrf().disable();
        http.authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/user/login", "/user/registration").permitAll()
                .anyRequest().authenticated()
                .and()
                .cors();
        http.addFilter(new CustomUsernamePasswordAuthenticationFilter(jwtService, authenticationManager));
        http.addFilterBefore(new JwtAuthenticationFilter(jwtService), CustomUsernamePasswordAuthenticationFilter.class);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring().requestMatchers("/css/**", "/js/**", "/images/**"));
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

    @Bean
    public byte[] secretKey() {
        return Decoders.BASE64.decode("testkeytestkeytestkeytestkeytestkeytestkeytestkeytestkeytestkeytestkeytestkeytestkey");
    }
}
