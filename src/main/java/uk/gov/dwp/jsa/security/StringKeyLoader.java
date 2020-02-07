package uk.gov.dwp.jsa.security;

import io.jsonwebtoken.io.Decoders;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Component
public class StringKeyLoader implements KeyLoader<String> {

    @Override
    public PublicKey loadPublicKey(final String keyToLoad) {
        try {
            byte[] publicBytes = Decoders.BASE64.decode(keyToLoad);
            X509EncodedKeySpec publicSpec = new X509EncodedKeySpec(publicBytes);
            return KeyFactory.getInstance("RSA").generatePublic(publicSpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            throw new KeyLoaderException(ex);
        }
    }

    @Override
    public PrivateKey loadPrivateKey(final String keyToLoad) {
        try {
            byte[] privateBytes = Decoders.BASE64.decode(keyToLoad);
            PKCS8EncodedKeySpec privateSpec = new PKCS8EncodedKeySpec(privateBytes);
            return KeyFactory.getInstance("RSA").generatePrivate(privateSpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            throw new KeyLoaderException(ex);
        }
    }

    static class KeyLoaderException extends RuntimeException {
        KeyLoaderException(final Throwable cause) {
            super(cause);
        }
    }
}
