package br.com.auth_server.controller;

import br.com.auth_server.dto.request.AuthRequest;
import br.com.auth_server.dto.response.AuthResponse;
import br.com.auth_server.exception.BusinessException;
import br.com.auth_server.service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class TokenController {

    private final AuthService authService;

    @PostMapping(value = "/token")
    public ResponseEntity<AuthResponse> token(@RequestBody AuthRequest request) throws JsonProcessingException {
        var token = authService.token(request);
        if(Objects.isNull(request.getClientId()) || Objects.isNull(request.getClientSecret())){
            throw new BusinessException(ErrorMessageEnum.AUT01);
        }
       return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
