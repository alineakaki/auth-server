package br.com.auth_server.security;

import br.com.auth_server.mocks.StringMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class KeyProviderTest {

    @InjectMocks
    private KeyProvider keyProvider;

    @Test
    void whenStringToPrivateKey_thenReturnPrivateKey() throws InvalidKeySpecException, NoSuchAlgorithmException {

        var privateKey = keyProvider.stringToPrivateKey(StringMocks.getPrivateKeyFromString());

        assertNotNull(privateKey);
        assertTrue(privateKey instanceof PrivateKey);
    }

    @Test
    void whenStringToPublicKey_thenReturnPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        var publicKeyPEM = "-----BEGIN PUBLIC KEY-----\n" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA3IslbpWIa8QoSToEaMXn\n" +
                "q9Y5NTq0dM16ZpDcP5ShRbKmnTe8U3YoXiPFEy48IpcRE3iBavIBaMFeGe1zeEOW\n" +
                "DjO1fnJpWn1keOKDWve8+Bg5bqW+D+ypiRJOHBtvKviUPaCWJJMU8aKrGju/qYjy\n" +
                "53jrO1H7IDhNVysWEyddBhPTxEvMLdDX7XpkjepKqX6+7SDjtrJgAvuBEWJF6Ict\n" +
                "YpXn5V7ySz+OP0C55rdx6jkoLLKIPL/Gor+OSG6NACe9KIHZSu1FIvwSIppZ9Q0c\n" +
                "J8/zKzh9ZJSIxt03baLFPUw4jVXnzFmhkyvvPLf6oF2ZE08riLZIxEODgupRBcgv\n" +
                "eQIDAQAB\n" +
                "-----END PUBLIC KEY-----";

        var publicKey = keyProvider.stringToPublicKey(publicKeyPEM);

        assertNotNull(publicKey);
        assertTrue(publicKey instanceof RSAPublicKey);
    }
}
