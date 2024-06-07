package com.Jungle.jungleboard.global.config;

import com.Jungle.jungleboard.global.auth.jwt.JwtAccessDeniedHandler;
import com.Jungle.jungleboard.global.auth.jwt.JwtAuthenticationEntryPoint;
import com.Jungle.jungleboard.global.auth.jwt.JwtFilter;
import com.Jungle.jungleboard.global.auth.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtProvider jwtTokenProvider;
    private final JwtFilter jwtFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/member/login", "/", "/member/join").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()//swagger 사용을 위함
                        .anyRequest().authenticated()
                );
        //authentication
//        http
//                .addFilterBefore(
//                        new JwtFilter(jwtTokenProvider),
//                        UsernamePasswordAuthenticationFilter.class)
//                .exceptionHandling((exceptionConfig) -> exceptionConfig
//                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                        .accessDeniedHandler(jwtAccessDeniedHandler)
//                );
        http
                .addFilterBefore(jwtFilter, BasicAuthenticationFilter.class);

        return http.build();
    }
}
