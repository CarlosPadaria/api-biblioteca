package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Autowired
    private AutenticacaoEntryPoint autenticacaoEntryPoint;



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.
        cors().and().csrf().disable()

                .authorizeHttpRequests(
                        requests -> requests

                                .requestMatchers(HttpMethod.GET, "/usuarios")
                                .hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/usuarios/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/usuarios/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/usuarios/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/livros/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/emprestimos/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/emprestimos/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/emprestimos/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/emprestimos/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/livros/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/livros/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/livros/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                                .requestMatchers(HttpMethod.GET, "/enderecos/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/enderecos/**").hasRole("ADMIN")
                                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                              //  .requestMatchers("/swagger-ui/**").permitAll()

                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(autenticacaoEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .build();

    }
}
