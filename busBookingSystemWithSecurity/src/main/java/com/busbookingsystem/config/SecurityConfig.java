package com.busbookingsystem.config;

import com.busbookingsystem.filter.JwtAuthFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    JwtAuthFilter jwtAuthFilter;
    @Autowired
    private UserDetailsService userDetailsService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.
                headers(headers -> headers.frameOptions(frame -> frame.disable()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm-> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exception-> exception.authenticationEntryPoint((request, response, authException) -> {
                            String path=request.getRequestURI();
                            if(path.startsWith("/swagger-ui")||path.startsWith("/v3/api-docs")||path.startsWith("/h2-console")|| path.equals("/favicon.ico")
                            || path.startsWith("/actuator")){
                                System.out.println(request.getRequestURI());
                                return;
                            }
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.setContentType("application/json");
                            response.getWriter().write("{\"error\": \"Unauthorized or invalid token\"}");
                        })

                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            response.setContentType("application/json");
                            response.getWriter().write("{\"error\": \"Access Denied\"}");
                        }))

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/authenticate",
                                "/register/**",
                                "/hello/**",
                                "/user/**",
                                "/h2-console/**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/favicon.ico",
                                "/actuator/**"
                        ).permitAll()
                        .anyRequest()
                        .authenticated())

//                        .requestMatchers(HttpMethod.GET,"/bus/**").hasAuthority(Permissions.READ.name())
//                        .requestMatchers(HttpMethod.PUT,"/bus/**").hasAuthority(Permissions.WRITE.name())
//                        .requestMatchers(HttpMethod.DELETE,"/bus/**").hasAuthority(Permissions.DELETE.name())


                 .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

                //.httpBasic(withDefaults());
        return http.build();
    }
//    @Bean
//    public UserDetailsService userDetailsService(){
//         return new CustomUserDetailsService();
   // }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,PasswordEncoder passwordEncoder){
        DaoAuthenticationProvider daoAuthenticationProvider= new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(daoAuthenticationProvider);
    }

}
