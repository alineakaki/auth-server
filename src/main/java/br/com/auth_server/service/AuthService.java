package br.com.auth_server.service;

import br.com.auth_server.dto.request.AuthRequest;
import br.com.auth_server.dto.response.AuthResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface AuthService {
    AuthResponse token(AuthRequest request) throws JsonProcessingException;
}
