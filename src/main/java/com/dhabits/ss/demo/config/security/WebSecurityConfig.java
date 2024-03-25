package com.dhabits.ss.demo.config.security;


import com.dhabits.ss.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final UserService userService;

    private static final String[] PERMIT_URLS = {
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
            "/swagger-resources/**"
    };


/*    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web
                .ignoring()
                .requestMatchers(PERMIT_URLS)
        );
    }*/

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
//                .headers(x -> x.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
//                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
//                .formLogin(AbstractHttpConfigurer::disable)
                .userDetailsService(userService)
                .authorizeHttpRequests(authz -> authz
//                        .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
                                .requestMatchers("/admin").hasRole("ADMIN")
                                .requestMatchers("/user").hasRole("USER")
                                .requestMatchers(PERMIT_URLS).permitAll()
                                .requestMatchers(HttpMethod.POST, "/resource").permitAll()
                                .requestMatchers(HttpMethod.POST, "/resource/role").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
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
