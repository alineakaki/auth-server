package br.com.auth_server.controller;

import br.com.auth_server.dto.request.AuthRequest;
import br.com.auth_server.dto.response.TokenResponse;
import br.com.auth_server.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static br.com.auth_server.util.JwtConstants.BEARER_PREFIX;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class JwtController {

    private final JwtService jwtService;

    @PostMapping(value = "/jwt")
    public ResponseEntity<TokenResponse> token(@RequestBody AuthRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(
                TokenResponse.builder().token(jwtService.createJwt(request))
                        .refreshToken(UUID.randomUUID().toString())
                        .tokenType(BEARER_PREFIX)
                        .build());
    }
}
