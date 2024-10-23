package br.com.auth_server.security;

import br.com.auth_server.service.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class JWTAuthenticationFilterTest {

    @Mock
    private AuthService authService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private FilterChain filterChain;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtAuthenticationFilter = new JWTAuthenticationFilter(authenticationManager, authService);
    }

    @Test
    void testDoFilterInternalWithValidToken() throws IOException, ServletException {
        var validToken = "Bearer valid.jwt.token";
        when(request.getHeader("Authorization")).thenReturn(validToken);

        UsernamePasswordAuthenticationToken authentication = mock(UsernamePasswordAuthenticationToken.class);
        when(authService.getAuthentication(validToken)).thenReturn(authentication);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(authService).getAuthentication(validToken);
        verify(filterChain).doFilter(request, response);
        assert SecurityContextHolder.getContext().getAuthentication() == authentication;
    }

    @Test
    void testDoFilterInternalWithNoToken() throws IOException, ServletException {
        when(request.getHeader("Authorization")).thenReturn(null);
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(authService, never()).getAuthentication(anyString());
        verify(filterChain).doFilter(request, response);
        assert SecurityContextHolder.getContext().getAuthentication() == null;
    }

    @Test
    void testDoFilterInternalWithInvalidToken() throws IOException, ServletException {
        var invalidToken = "Bearer invalid.jwt.token";
        when(request.getHeader("Authorization")).thenReturn(invalidToken);

        when(authService.getAuthentication(invalidToken)).thenReturn(null);
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(authService).getAuthentication(invalidToken);
        verify(filterChain).doFilter(request, response);
        assert SecurityContextHolder.getContext().getAuthentication() == null;
    }
}
