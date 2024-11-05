package DoAn.E_LearningEducation.SecurityConfig;

import DoAn.E_LearningEducation.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final String[] PUBLIC_ENDPOINTS = { "/menus", "/login-user", "/admin/**",
            "/node_modules/**", "/get_blogs", "/auth/verify", "/auth/check-login", "/",
             "/css/**", "/js/**", "/lib/**", "/img/**", "/images/**", "/vendor/**"};

    private final String[] PUBLIC_POST_ENDPOINTS = { "/auth/check-login", "/create_user" };

    @Value("${jwt.signerKey}")
    private String signerKey;

    private final CustomAuthenticationEntrypoint customAuthenticationEntrypoint;
    private final AuthenticationService authenticationService;

    public SecurityConfig(CustomAuthenticationEntrypoint customAuthenticationEntrypoint, AuthenticationService authenticationService) {
        this.customAuthenticationEntrypoint = customAuthenticationEntrypoint;
        this.authenticationService = authenticationService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, PUBLIC_ENDPOINTS).permitAll()
                        .requestMatchers(HttpMethod.POST, PUBLIC_POST_ENDPOINTS).permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(customAuthenticationEntrypoint)
                )
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(new JwtAuthenticationFilter(authenticationService, customAuthenticationEntrypoint),
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
