package com.example.thymeleafendpoints.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(authorize -> authorize.requestMatchers("/customers").permitAll().anyRequest().authenticated()).formLogin().and().csrf();
        return httpSecurity.build();
    }
}
