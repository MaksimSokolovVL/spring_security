package com.dhabits.ss.demo.config.security;


import com.dhabits.ss.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final UserService userService;

    private static final String[] PERMIT_ALL_URLS = {
            "/",
            "/h2",
            "/header",
            "/index",
            "/registration",
            "/h2/**",
            "/js/**",
            "/css/**",
            "/img/**",
            "/swagger-ui/**",
            "/v3/**",
            "/error",
            "/swagger-resources/**",
            "/api/v1/auth/access_token"
    };
    private static final String[] IGNORED_RQ = {
            "/api/v1/auth/access_token",
            "/logout"
    };


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
//                .csrf(AbstractHttpConfigurer::disable)
                .csrf(cs -> cs.ignoringRequestMatchers(IGNORED_RQ))
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/user").hasRole("USER")
                        .requestMatchers(PERMIT_ALL_URLS).permitAll()
                        .requestMatchers(HttpMethod.POST, "/resource").permitAll()
                        .requestMatchers(HttpMethod.POST, "/resource/role").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .userDetailsService(userService)
                .httpBasic(Customizer.withDefaults())
                .anonymous(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> sm.sessionCreationPolicy(
                        SessionCreationPolicy.IF_REQUIRED
                ))
//                .formLogin(AbstractHttpConfigurer::disable)
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll()
                )
                .build();
    }
}
