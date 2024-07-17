package br.com.auth_server.service;

import br.com.auth_server.dto.request.AuthRequest;

public interface JwtService {
    public String createJwt(AuthRequest request);
}
