package com.cuttingEdge.bunan.config;

import com.cuttingEdge.bunan.config.jwt.JwtFilter;
import com.cuttingEdge.bunan.config.jwt.JwtUtil;
import com.cuttingEdge.bunan.constant.Permission;
import com.cuttingEdge.bunan.constant.Role;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import javax.servlet.Filter;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
//    @Bean
//    public WebSecurityCustomizer ignoringCustomizer() {
//
//        return (web) -> web.ignoring().antMatchers("/**");
//    }


    private final JwtFilter JwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {


        return httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .cors().configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Arrays.asList("https://my-bunan-f2h9wqsfw-kangshingyus-projects.vercel.app","https://www.bunan.co.kr","http://localhost:5173"));
                    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE","PATCH","OPTIONS"));
                    config.setAllowedHeaders(Arrays.asList("*"));

                    return config;
                })
                .and()
                .authorizeRequests()
//                .antMatchers("/api/v1/management/**").hasAnyRole(Role.ADMIN.name(),Role.MANAGER.name())
//                .antMatchers(HttpMethod.GET,"/api/v1/management/**").hasAnyAuthority(Permission.ADMIN_READ.name(),Permission.MANAGER_READ.name())
//                .antMatchers(HttpMethod.POST,"/api/v1/management/**").hasAnyAuthority(Permission.ADMIN_CREATE.name(),Permission.MANAGER_CREATE.name())
//                .antMatchers(HttpMethod.PUT,"/api/v1/management/**").hasAnyAuthority(Permission.ADMIN_UPDATE.name(),Permission.MANAGER_UPDATE.name())
//                .antMatchers(HttpMethod.DELETE,"/api/v1/management/**").hasAnyAuthority(Permission.ADMIN_DELETE.name(),Permission.MANAGER_DELETE.name())//@PreAuthorize(hasRole)를 쓰는게 낫다
                .antMatchers("/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS,"/**/*").permitAll() //preflight
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(JwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
