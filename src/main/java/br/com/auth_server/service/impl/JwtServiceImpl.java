package br.com.auth_server.service.impl;

import br.com.auth_server.dto.request.AuthRequest;
import br.com.auth_server.security.KeyProvider;
import br.com.auth_server.service.JwtService;
import br.com.auth_server.util.JwtConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.time.ZonedDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtServiceImpl implements JwtService {

    private final KeyProvider jwtKeyProvider;

    @Override
    public String createJwt(AuthRequest request) {
        PrivateKey privateKey = null;
        try {
            privateKey = jwtKeyProvider.stringToPrivateKey(request.getPrivateKey());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error("######## Error > getPrivateKey : {}", e.getMessage());
        }

        return Jwts.builder()
                .setHeaderParam(JwtConstants.IAT, System.currentTimeMillis())
                .claim(JwtConstants.CLIENT_ID, request.getClientId())
                .claim(JwtConstants.SCOPE, JwtConstants.GRANT_TYPE)
                .setExpiration(Date.from(ZonedDateTime.now().plusHours(2).toInstant()))
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }
}
