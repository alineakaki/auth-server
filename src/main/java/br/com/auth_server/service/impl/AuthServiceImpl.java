package br.com.auth_server.service.impl;

import br.com.auth_server.api.OAuthClient;
import br.com.auth_server.dto.request.AuthRequest;
import br.com.auth_server.dto.response.AuthResponse;
import br.com.auth_server.dto.request.TokenRequest;
import br.com.auth_server.service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    @Value("${oauth.client-id}")
    private String clientId;

    @Value("${oauth.client-secret}")
    private String clientSecret;

    @Value("${oauth.issuer}")
    private String oAuthIssuer;

    private final OAuthClient oAuthClient;
    private static final String GRANT_TYPE = "client_credentials";

    @Override
    public AuthResponse token(AuthRequest request) throws JsonProcessingException {
        var response = oAuthClient.token(TokenRequest.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .audience(oAuthIssuer.concat("/api/v2/"))
                .grantType(GRANT_TYPE).build());
        return Objects.nonNull(response) ? response : null;
    }
}
