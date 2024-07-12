package br.com.auth_server.controller;

import br.com.auth_server.dto.response.TokenResponse;
import br.com.auth_server.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class JwtController {

    private final JwtService jwtService;

    @GetMapping(value = "/jwt")
    public ResponseEntity<TokenResponse> token()  {
        return ResponseEntity.status(HttpStatus.OK).body(TokenResponse.builder().token(jwtService.createJwt()).build());
    }
}
