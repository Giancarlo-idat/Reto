package com.civa.futbolista_posicion.security;


import com.civa.futbolista_posicion.security.filter.JwtTokenValidator;
import com.civa.futbolista_posicion.security.jwt.JwtUtils;
import com.civa.futbolista_posicion.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtUtils jwtUtils;

    public SecurityConfig(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    http.requestMatchers(HttpMethod.POST, "/auth/login").permitAll();

                    http.requestMatchers(HttpMethod.GET, "/users/**").hasAnyAuthority("ROLE_Admin");
                    http.requestMatchers(HttpMethod.POST, "/users/**").hasAnyAuthority("ROLE_Admin");
                    http.requestMatchers(HttpMethod.PUT, "/users/**").hasAnyAuthority("ROLE_Admin");
                    http.requestMatchers(HttpMethod.DELETE, "/users/**").hasAnyAuthority("ROLE_Admin");

                    http.requestMatchers(HttpMethod.GET, "/roles/**").hasAnyAuthority("ROLE_Admin");
                    http.requestMatchers(HttpMethod.POST, "/roles/**").hasAnyAuthority("ROLE_Admin");
                    http.requestMatchers(HttpMethod.PUT, "/roles/**").hasAnyAuthority("ROLE_Admin");
                    http.requestMatchers(HttpMethod.DELETE, "/roles/**").hasAnyAuthority("ROLE_Admin");


                    http.requestMatchers(HttpMethod.GET, "/futbolista/**").hasAnyAuthority("ROLE_Admin");
                    http.requestMatchers(HttpMethod.POST, "/futbolista/**").hasAnyAuthority("ROLE_Admin");
                    http.requestMatchers(HttpMethod.PUT, "/futbolista/**").hasAnyAuthority("ROLE_Admin");
                    http.requestMatchers(HttpMethod.DELETE, "/futbolista/**").hasAnyAuthority("ROLE_Admin");


                    http.requestMatchers(HttpMethod.GET, "/posicion/**").hasAnyAuthority("ROLE_Admin");
                    http.requestMatchers(HttpMethod.POST, "/posicion/**").hasAnyAuthority("ROLE_Admin");
                    http.requestMatchers(HttpMethod.PUT, "/posicion/**").hasAnyAuthority("ROLE_Admin");
                    http.requestMatchers(HttpMethod.DELETE, "/posicion/**").hasAnyAuthority("ROLE_Admin");


                    http.requestMatchers(HttpMethod.GET, "/futbolista-posicion/**").hasAnyAuthority("ROLE_Admin");
                    http.requestMatchers(HttpMethod.POST, "/futbolista-posicion/**").hasAnyAuthority("ROLE_Admin");
                    http.requestMatchers(HttpMethod.PUT, "/futbolista-posicion/**").hasAnyAuthority("ROLE_Admin");
                    http.requestMatchers(HttpMethod.DELETE, "/futbolista-posicion/**").hasAnyAuthority("ROLE_Admin");

                    // Bloquear todas las demás solicitudes
                    http.anyRequest().denyAll();
                })
                .addFilterBefore(new JwtTokenValidator(jwtUtils), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsServiceImpl userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
