package com.payment.upi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**").ignoringRequestMatchers("/saveUser"))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register", "/login", "styles.css", "/h2-console/**").permitAll()
                        .requestMatchers("/saveUser").permitAll()
                .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .failureUrl("/login?error=true"))
                .logout(logout -> logout.logoutSuccessUrl("/login?logout=true"))
                .headers(headersConfigurer -> headersConfigurer
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));


        return http.build();

    }

//    @Bean
//    public PasswordEncoder passwordEncoder () {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder){
//
//        UserDetails user = User.withUsername("demo")
//                .password(passwordEncoder.encode("qwerty1234"))
//                .roles("user")
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
//
//
//    }
}
