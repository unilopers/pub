package com.grupo_5.pub.Infra.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
<<<<<<< HEAD
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity

public class SecurityConfig {

=======
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

>>>>>>> 9f6a77b (subindo projeto para fork)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
<<<<<<< HEAD
                //Esses endpoints qualquer um pode acessar
                .requestMatchers("/login").permitAll()
                .requestMatchers("/cadastro").permitAll()

                //Esses endpoints estão exigindo que tenha uma autorização pra acessar
                //Os ** na frente vão exigir que tudo que começa com o endpoint precisa estar autenticado
=======
                .requestMatchers("/auth/login").permitAll()
                .requestMatchers("/auth/cadastro").permitAll()
>>>>>>> 9f6a77b (subindo projeto para fork)
                .requestMatchers("/ingredientes/**").authenticated()
                .requestMatchers("/clientes/**").authenticated()
                .requestMatchers("/comandas/**").authenticated()
                .requestMatchers("/eventos/**").authenticated()
                .requestMatchers("/bebidas/**").authenticated()
                .requestMatchers("/mesas/**").authenticated()
                .requestMatchers("/api/promocoes/**").authenticated()
<<<<<<< HEAD

                .anyRequest().authenticated()
            )
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
=======
                .anyRequest().authenticated()
            )
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
>>>>>>> 9f6a77b (subindo projeto para fork)
}