package com.example.TaskManager.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebAuthorizationConfig {

    private final CustomAuthenticationProvider authenticationProvider;

    public WebAuthorizationConfig(CustomAuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authenticationProvider(authenticationProvider)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/users/**").hasRole("ADMIN")
                        .requestMatchers("/tasks/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/register").permitAll()
                );
//                .formLogin(Customizer.withDefaults());
        return http.build();
    }

//    @Bean
//    public AuthenticationManager authenticationManager() {
//        return new ProviderManager(authenticationProvider());
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        return daoAuthenticationProvider;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        String idForEncode = "bcrypt";
//        Map<String, PasswordEncoder> encoders = new HashMap<>();
//        encoders.put(idForEncode, new BCryptPasswordEncoder());
//        encoders.put("pbkdf2", Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8());
//        encoders.put("scrypt", SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8());
//        encoders.put("argon2", Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8());
//        return new DelegatingPasswordEncoder(idForEncode, encoders);
//    }
//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowCredentials(true);
//        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:8080"));
//        configuration.setAllowedHeaders(Collections.singletonList("Content-Type"));
//        configuration.setAllowedMethods(Collections.singletonList("*"));
//        configuration.setMaxAge(Duration.of(3600, ChronoUnit.SECONDS));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
}
