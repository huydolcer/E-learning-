package DoAn.E_LearningEducation.SecurityConfig;

import DoAn.E_LearningEducation.Service.AuthenticationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final AuthenticationService authenticationService;
    private final CustomAuthenticationEntrypoint customAuthenticationEntrypoint;

    // Danh sách các URL công khai để bỏ qua xác thực
    private final List<String> publicEndpoints = List.of(
            "/menus", "/login-user", "/admin/login", "/node_modules/**",
            "/auth/check-login", "/", "/css/**", "/js/**",
            "/lib/**", "/img/**", "/images/**", "/vendor/**"
    );

    public JwtAuthenticationFilter(AuthenticationService authenticationService, CustomAuthenticationEntrypoint customAuthenticationEntrypoint) {
        this.authenticationService = authenticationService;
        this.customAuthenticationEntrypoint = customAuthenticationEntrypoint;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        // Bỏ qua các endpoint công khai
        return publicEndpoints.stream().anyMatch(requestURI::startsWith);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    token = cookie.getValue();
                    System.out.println("Token in cookie By JWTAuthentication = " + token);
                    break;
                }
            }
        }

        if (token != null && authenticationService.validateToken(token)) {
            authenticationService.setAuthenticationFromToken(token);
        } else {
            System.out.println("Token không hợp lệ hoặc không tồn tại");
            //response.sendRedirect("/learning_edu/admin/login");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
