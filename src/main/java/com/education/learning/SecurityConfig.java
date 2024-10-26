package com.education.learning;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity 
public class SecurityConfig {
@Bean
public SecurityFilterChain filtragem(HttpSecurity http
		) throws Exception{
    http.csrf().disable() 
    .authorizeHttpRequests(auth -> auth.requestMatchers("/home/cadastrar", "/home/login").permitAll().anyRequest().authenticated()
    );	
    
    
    return http.build();
}
}