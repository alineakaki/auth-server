package br.com.auth_server.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class JwtResponse implements Serializable  {
    private String clientId;
    private String scope;
    private long exp;
}
