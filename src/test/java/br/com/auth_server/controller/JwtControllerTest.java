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
    void whenRequestOk_thenReturnToken() {
        AuthRequest request = new AuthRequest("clientId", "privateKey");
        String token = "testToken";
        when(jwtService.createJwt(request)).thenReturn(token);
        ResponseEntity<TokenResponse> response = jwtController.token(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(token, Objects.requireNonNull(response.getBody()).getToken());
    }
}