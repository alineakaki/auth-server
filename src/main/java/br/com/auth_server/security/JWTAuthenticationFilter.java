package br.com.auth_server.security;

import br.com.auth_server.service.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Objects;

public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    private final AuthService authService;
    private static final String HEADER_STRING = "Authorization";

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, AuthService authService) {
        super(authenticationManager);
        this.authService = authService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {


        var token = req.getHeader(HEADER_STRING);
        if (Objects.nonNull(token)) {
            UsernamePasswordAuthenticationToken auth = authService.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
            chain.doFilter(req, res);
        } else {
            chain.doFilter(req, res);
        }
    }
}
