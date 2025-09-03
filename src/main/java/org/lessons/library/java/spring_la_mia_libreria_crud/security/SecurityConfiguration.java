package org.lessons.library.java.spring_la_mia_libreria_crud.security;



import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) 
    throws Exception {
        http.authorizeHttpRequests(requests -> requests
        .requestMatchers("/books/create", "/books/edit/**").hasAuthority("ADMIN")
        .requestMatchers(HttpMethod.POST, "/books/**").hasAuthority("ADMIN")
        .requestMatchers("/genres/create", "/genres/edit/**").hasAuthority("ADMIN")
        .requestMatchers(HttpMethod.POST, "/genres/**").hasAuthority("ADMIN")
        .requestMatchers("/genres/delete/**", "/books/delete/**").hasAuthority("ADMIN")
        .requestMatchers("/genres", "/genres/**").hasAnyAuthority("USER", "ADMIN")
        .requestMatchers("/books", "/books/**").hasAnyAuthority("USER", "ADMIN")
        .requestMatchers("/api/**").permitAll()
        .requestMatchers("/**").permitAll())
        .formLogin(Customizer.withDefaults())
        .logout(logout -> logout
            .logoutSuccessUrl("/") // vai alla home dopo il logout
            .permitAll()
        )
        .exceptionHandling(Customizer.withDefaults())
        .cors(cors -> cors.configurationSource(_-> {
            var corsConfig = new org.springframework.web.cors.CorsConfiguration();
            corsConfig.setAllowedOrigins(List.of("http://localhost:5173"));
            corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
            corsConfig.setAllowedHeaders(List.of("*"));
            corsConfig.setAllowCredentials(true);
            return corsConfig;
        }))
        // .cors(cors -> cors.disable())
        .csrf(csrf -> csrf.disable());
        

        return http.build();
    }

    @Bean
    @SuppressWarnings("deprecation")
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }

    @Bean
    DatabaseUserDetailService userDetailsService() {
        return new DatabaseUserDetailService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
}
}
