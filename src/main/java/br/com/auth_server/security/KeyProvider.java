package br.com.auth_server.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class KeyProvider {

    private static final String RSA = "RSA";

    public PrivateKey stringToPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        var cleanedPrivateKeyString = removeStringPrivateKey(privateKey);
        var keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(cleanedPrivateKeyString));
        return KeyFactory.getInstance(RSA).generatePrivate(keySpec);
    }

    public RSAPublicKey stringToPublicKey(String publicKey) throws InvalidKeySpecException, NoSuchAlgorithmException {
        var kf = KeyFactory.getInstance(RSA);
        var keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(removeStringPublicKey(publicKey)));
        return (RSAPublicKey) kf.generatePublic(keySpecX509);
    }

    private String removeStringPrivateKey(String key) {
        return key.replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");
    }

    private String removeStringPublicKey(String key) {
        return key.replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");
    }
}
