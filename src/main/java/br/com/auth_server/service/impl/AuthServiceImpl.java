package br.com.auth_server.service.impl;

import br.com.auth_server.dto.response.JwtResponse;
import br.com.auth_server.security.KeyProvider;
import br.com.auth_server.service.AuthService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Objects;

import static br.com.auth_server.util.JwtConstants.BEARER_PREFIX;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    @Value("${public.key}")
    private String publicKeyString;

    private final KeyProvider keyProvider;

    @Override
    public UsernamePasswordAuthenticationToken getAuthentication(String token) throws JsonProcessingException {
        String payloadB64 = null;
        try {
            payloadB64 = JWT
                    .require(Algorithm.RSA256(keyProvider.stringToPublicKey(publicKeyString), null))
                    .build()
                    .verify(token.replace(BEARER_PREFIX, Strings.EMPTY))
                    .getPayload();
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            log.error("######## Error > publicKey : {}", e.getMessage());
        }

        var payload = new String(Base64.getDecoder().decode(payloadB64));

        var objectMapper = new ObjectMapper();
        JwtResponse payloadModel = objectMapper.readValue(payload, JwtResponse.class);

        if (Objects.nonNull(payloadModel.getClientId())){
            return new UsernamePasswordAuthenticationToken(payloadModel, null, new ArrayList<>());
        }
        return null;
    }
}