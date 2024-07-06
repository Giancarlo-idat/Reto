package com.civa.futbolista_posicion.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    private static final String URL_FRONTEND = "http://localhost:3001";
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(URL_FRONTEND) // Permitir solicitudes desde http://localhost:3001
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Permitir los métodos HTTP especificados
                .allowedHeaders("*"); // Permitir todos los encabezados en las solicitudes
    }
}
