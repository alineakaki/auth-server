package br.com.auth_server.service.impl;

import br.com.auth_server.security.KeyProvider;
import br.com.auth_server.service.JwtService;
import br.com.auth_server.util.ConstantsUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${client.id}")
    String clientId;

    @Value("${private.key}")
    String privateKeyString;

    private final KeyProvider jwtKeyProvider;

    @Override
    public String createJwt() {
        PrivateKey privateKey = null;
        try {
            privateKey = jwtKeyProvider.stringToPrivateKey(privateKeyString);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error("######## Error > getPrivateKey : {}", e.getMessage());
        }

        return Jwts.builder()
                .setHeaderParam(ConstantsUtil.IAT, System.currentTimeMillis())
                .claim(ConstantsUtil.CLIENT_ID, clientId)
                .claim(ConstantsUtil.SCOPE, ConstantsUtil.GRANT_TYPE)
                .setExpiration(Date.from(ZonedDateTime.now().plusHours(2).toInstant()))
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }
}
