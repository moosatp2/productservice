//package com.example.productservice.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//public class SpringSecurityConfig {
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorize -> authorize
////                        .requestMatchers("/products/{id}").authenticated()
////                                .requestMatchers("/products").hasAuthority("ADMIN")
////                        .requestMatchers("/messages/**").access(hasScope("messages"))
//                        .anyRequest().permitAll()
//                )
////                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));
//        return http.build();
//    }
//
//}
