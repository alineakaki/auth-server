package br.com.auth_server.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TokenResponse {
    private String token;
    private String refreshToken;
    private String tokenType;
}
