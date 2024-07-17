package br.com.auth_server.controller;

import br.com.auth_server.dto.request.AuthRequest;
import br.com.auth_server.dto.response.TokenResponse;
import br.com.auth_server.service.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JwtControllerTest {

    @InjectMocks
    private JwtController jwtController;

    @Mock
    private JwtService jwtService;

    @Test
    void testToken() {
        var expectedToken = "testToken";
        when(jwtService.createJwt(new AuthRequest())).thenReturn(expectedToken);
        ResponseEntity<TokenResponse> response = jwtController.token(new AuthRequest());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedToken, Objects.requireNonNull(response.getBody()).getToken());
    }
}