package com.cuttingEdge.bunan.config;

import com.cuttingEdge.bunan.config.jwt.JwtFilter;
import com.cuttingEdge.bunan.config.jwt.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
//    @Bean
//    public WebSecurityCustomizer ignoringCustomizer() {
//
//        return (web) -> web.ignoring().antMatchers("/**");
//    }

    private final JwtUtil jwtUtil;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {


        return httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .cors().configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Arrays.asList("https://my-bunan-f2h9wqsfw-kangshingyus-projects.vercel.app","https://www.bunan.co.kr"));
                    return config;
                })
                .and()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/**").authenticated()
//                .antMatchers(HttpMethod.GET, "/api/**").authenticated()
//                .antMatchers(HttpMethod.PATCH, "/api/**").authenticated()
//                .antMatchers(HttpMethod.DELETE, "/api/**").authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
