package com.cuttingEdge.bunan.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("http://localhost:5173","https://my-bunan-f2h9wqsfw-kangshingyus-projects.vercel.app","https://www.bunan.co.kr")
                .allowedMethods("*")
                .allowedHeaders("*")
                .maxAge(3600);
    }


}

