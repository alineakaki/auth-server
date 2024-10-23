package br.com.auth_server.service.impl;

import br.com.auth_server.dto.request.AuthRequest;
import br.com.auth_server.mocks.StringMocks;
import br.com.auth_server.security.KeyProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class JwtServiceImplTest {

    @InjectMocks
    private JwtServiceImpl jwtService;

    @Mock
    private KeyProvider jwtKeyProvider;

    private PrivateKey privateKey;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        privateKey = generatePrivateKey();
    }

    @Test
    void testCreateJwt_thenReturn() throws Exception {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setPrivateKey(StringMocks.getPrivateKeyFromString());
        authRequest.setClientId("testClient");

        when(jwtKeyProvider.stringToPrivateKey(authRequest.getPrivateKey())).thenReturn(privateKey);
        assertNotNull(jwtService.createJwt(authRequest));
    }

    private PrivateKey generatePrivateKey() throws Exception {
        var privateKeyPEM = StringMocks.getPrivateKeyFromString();
        var privateKeyContent = privateKeyPEM
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s+", "");

        byte[] encoded = Base64.getDecoder().decode(privateKeyContent);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        return KeyFactory.getInstance("RSA").generatePrivate(keySpec);
    }
}
