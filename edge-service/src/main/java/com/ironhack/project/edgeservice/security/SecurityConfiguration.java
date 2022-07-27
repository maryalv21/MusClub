package com.ironhack.project.edgeservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.csrf().disable();
        http.cors().disable();
        http.authorizeRequests()
                //.antMatchers(HttpMethod.GET, "/members").hasRole("MEMBER")
                //.antMatchers(HttpMethod.GET, "/members/**").hasRole("MEMBER")
                //.antMatchers(HttpMethod.POST, "/members").hasRole("MEMBER")
                //.antMatchers(HttpMethod.POST, "/members/**").hasRole("MEMBER")
                //.antMatchers(HttpMethod.PUT, "/members/**").hasRole("MEMBER")
                //.antMatchers(HttpMethod.DELETE, "/members/**").hasRole("MEMBER")
                //.antMatchers(HttpMethod.GET, "/users").authenticated()
                .antMatchers(HttpMethod.GET, "/login").authenticated()
                .antMatchers(HttpMethod.GET, "/login/**").authenticated()
                //.antMatchers(HttpMethod.GET, "/games").authenticated()
                //.antMatchers(HttpMethod.PUT, "/games/**").authenticated()
                //.antMatchers(HttpMethod.DELETE, "/games/**").authenticated()
                //.antMatchers(HttpMethod.GET, "/games/**").authenticated()
                .anyRequest().permitAll();
        return http.build();
    }

/*    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("ADMIN", "MEMBER", "player")
                .build();
        return new InMemoryUserDetailsManager(user);
    }*/

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws  Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
