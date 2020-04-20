package com.berla.pwrapps.isiapp.config;

import com.berla.pwrapps.isiapp.security.jwt.JwtConfigurer;
import com.berla.pwrapps.isiapp.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    private static final String LOGIN_REGISTER_ENDPOINTS = "/auth/**";

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(LOGIN_REGISTER_ENDPOINTS).permitAll()
                .antMatchers(
                        "/ride/**"
                ).hasAnyAuthority("ADMIN", "USER_APP")
                .antMatchers(
                    "/ride/add",
                        "/ride/get",
                        "/ride/getAll",
                        "/ride/getCost"
                ).hasRole("USER_APP")
                .antMatchers(
                        "/ride/get",
                        "/ride/getAll",
                        "/ride/accept",
                        "/ride/decline"
                ).hasRole("DRIVER_APP")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}