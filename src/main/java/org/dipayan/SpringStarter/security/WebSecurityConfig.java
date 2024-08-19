package org.dipayan.SpringStarter.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

import org.dipayan.SpringStarter.util.constants.Privillages;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
    
    private static final String[] WHITELIST = {
        "/",
        "/login",
        "/register",
        "/profile",
        // "/db-console/**",
        "/css/**",
        "/fonts/**",
        "/images/**",
        "/js/**",
        "/posts/**",
        "/update_photo",
        "/uploads/**",
        "/forget-password",
        "/reset-password",
        "/change-password",
        "/api/**"
    };

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers(headers -> headers.frameOptions().sameOrigin())
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(WHITELIST).permitAll()
                        .requestMatchers("/profile/**").authenticated()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/editor/**").hasAnyRole("ADMIN", "EDITOR")
                        .requestMatchers("/admin/**").hasAuthority(Privillages.ACCESS_ADMIN_PANEL.getPrivillage()))
                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/"))
                .rememberMe(me -> me.rememberMeParameter("remember-me"))
                .httpBasic(withDefaults());

        
        http.csrf(csrf -> csrf.disable());
        http.headers(headers -> headers.frameOptions().disable());

        return http.build();
    }

}
