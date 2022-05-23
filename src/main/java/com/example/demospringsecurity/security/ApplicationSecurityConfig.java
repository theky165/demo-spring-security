package com.example.demospringsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.demospringsecurity.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*") // check url condition
                .permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .anyRequest()
                .authenticated() // Request must be authenticated
                .and()
                .httpBasic(); // Basic Auth
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails studentUSer = User.builder()
                .username("student")
                .password(passwordEncoder.encode("student123"))
                .roles(STUDENT.name()) // = ROLE_STUDENT
                .build();

        UserDetails adminUSer = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles(ADMIN.name()) // = ROLE_ADMIN
                .build();

        return new InMemoryUserDetailsManager(
                studentUSer,
                adminUSer
        );
    }
}
