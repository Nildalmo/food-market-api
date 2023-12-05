package com.academinadodesenvolvedor.market.configs;

import com.academinadodesenvolvedor.market.filters.JwtFilter;
import com.academinadodesenvolvedor.market.services.contracts.JwtServiceContract;
import com.academinadodesenvolvedor.market.services.contracts.UserServiceContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration

public class SecurityConfiguration {
    private  final JwtServiceContract jwtService;
    private final UserServiceContract userService;
    @Autowired
    public SecurityConfiguration(JwtServiceContract jwtService,
                                 UserServiceContract userService){
        this.jwtService = jwtService;
        this.userService = userService;

        // new SecurityConfiguration(new JwtService(), new UserService(new UserRepositoryImpl(new EntityManager())));
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request -> request
                        .requestMatchers(HttpMethod.POST, "/login/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/stores/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/products/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/stores/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/stores/**" ).hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/stores/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/products/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/products/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/products/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/storage/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtFilter(this.jwtService, this.userService),
                        UsernamePasswordAuthenticationFilter.class)
        ;
        return http.build();
    }
}
