package br.com.auth_server.security;

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
        var privateKeyPEM = "-----BEGIN PRIVATE KEY-----\n" +
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDciyVulYhrxChJ\n" +
                "OgRoxeer1jk1OrR0zXpmkNw/lKFFsqadN7xTdiheI8UTLjwilxETeIFq8gFowV4Z\n" +
                "7XN4Q5YOM7V+cmlafWR44oNa97z4GDlupb4P7KmJEk4cG28q+JQ9oJYkkxTxoqsa\n" +
                "O7+piPLneOs7UfsgOE1XKxYTJ10GE9PES8wt0NftemSN6kqpfr7tIOO2smAC+4ER\n" +
                "YkXohy1ileflXvJLP44/QLnmt3HqOSgssog8v8aiv45Ibo0AJ70ogdlK7UUi/BIi\n" +
                "mln1DRwnz/MrOH1klIjG3TdtosU9TDiNVefMWaGTK+88t/qgXZkTTyuItkjEQ4OC\n" +
                "6lEFyC95AgMBAAECggEACVJQSLZFR9jIox+ypAMJYbfyPw+2JOGydLdWt9E+Z8W/\n" +
                "SRHcAeMSnbgBUmg33nZoIFyNi8miyZyyr4HbyY+BviPtXeTUEyENqBZa2vNxp0LO\n" +
                "2qBiwiWMnOCLMKDwN+69xUUOU4lr7zKi7L7twOqq6aGwv5cdeCUpD7AghLq7MdB8\n" +
                "l5R0I0N2RAGnBA1SG7SR29D4eQ5nzOBU1xbYEBptdj0jhkcR7q3Um5x5krfwLxvJ\n" +
                "pPSpHR6/gu+VTmVKznPvl31QBGVcmI7bb/dAYjQ+FxEIckmRHfSBWA31uT3ZctMZ\n" +
                "pI5WrC/uzP/pHFS/hIeh2gWYqpQoSrS+u3b88ACgiQKBgQD93SA3pjZHwExcb783\n" +
                "0xUSUXVUpuvp6w6/5svrUfeF0YgQ5Fo+QRsIKYrWJ/lPaAKog0btFSh/FWySCFka\n" +
                "KLshXvLcspYnOaJyVmAUbmhHxihedH9E34911jahQwY2/n1DnJykz9f3+2kHTsfV\n" +
                "XaPD0sXw6wsWfb6kVEqp/CdpswKBgQDeZj3nu5SBzuuUnydcrYBDxP9DdejDDWB9\n" +
                "Mx54/7A55XgksI8gj/yqLgJNi/UiCs95flMzeyZ4YNyqvSnavmcPLdd9FYhgdeCO\n" +
                "WNNI50GLbzqSX6nvLuuhlThxpHV9u4XnG/o1/8cR8Jo7RNMj2UMhzorUMyBNjTdK\n" +
                "vPlyRHJUIwKBgHV5eSonUSD83Jk94Ne9ZxfV+ByXLb5OVsH41E5t3MPhBT0D3rrG\n" +
                "N/Mb/jJBxz22nNxL+2tEijvctyQQluLjDE1R+cKlJZS7N+//BnEN8lWcXhvvfuFp\n" +
                "LqLR4O/Y6yxRsbey5k3iHEeV4LFQBGs47Mp2Y8Crk7Lxt2Gl2/MzSy2/AoGAa/Jw\n" +
                "ZcQ0hvUqf47oiiFkuoAp7lKS0enyRZLEXbKtlMx+jkBsmD+LQgbm80DVkNpybeQU\n" +
                "+k3yFEffk7gRUq1MVJ/JklsbQMa2YiFXHHGy7LzmTxnD3+aEVH+bT/2lUYvtqIl5\n" +
                "3y/xLhH8qnvzh9jyrrwTwn+2VC4RAmPLJ911KMUCgYEAjgcULqsHMfnpNoysR/rD\n" +
                "NaAQVsW979gPi9Dv2DMkfu/feJR3d5wI9gyoQJMVID4CVJzN0eHwuiqYDZUtmOO0\n" +
                "pxmuS0/xD2CqkBb+ZBBQGIZBAJlsCT+0gMlN+7Bo6oIsm6jXKVj5yGzl3a27eNKR\n" +
                "BWUTj2K9blyVVtoPHUE7MDc=\n" +
                "-----END PRIVATE KEY-----";

        var privateKey = keyProvider.stringToPrivateKey(privateKeyPEM);

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
