package uk.gov.dwp.jsa.security;

import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import uk.gov.dwp.jsa.adaptors.ServicesProperties;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class TokenProviderTest {

    private static final String PAYLOAD = "{\n" +
            "  \"id\": \"1234567890\",\n" +
            "  \"sub\": \"86152316253\",\n" +
            "  \"username\": \"First Last\",\n" +
            "  \"aud\": \"nsjsa_assistance_user\"\n" +
            "}";

    private JWTSigningKeyResolver signingKeyResolverMock;

    private String validToken;

    private TokenProvider sut;

    private ServicesProperties servicesProperties;

    private PrivateKey privateKey;

    @Before
    public void setUp() throws NoSuchAlgorithmException {

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();
        privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();
        validToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setPayload(PAYLOAD)
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();

        servicesProperties = new ServicesProperties();
        servicesProperties.setPublicKey(Encoders.BASE64.encode(publicKey.getEncoded()));

        signingKeyResolverMock = new JWTSigningKeyResolver(new PublicKeyRepo(servicesProperties, new StringKeyLoader()));

        sut = new TokenProvider(signingKeyResolverMock);
    }

    @Test
    public void testIfKeyIdNotExitsShouldNotBeValid() {

        validToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setPayload(PAYLOAD)
                .setHeaderParam(JwsHeader.KEY_ID, "myKeyId")
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();

        boolean result = sut.validateToken(validToken);

        assertFalse("Should not be valid", result);
    }


    @Test
    public void testIfKeyInvalidShouldNotBeValid() {

        String secret = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAi8KlznxFQMnN9V2302QK" +
                "L7coVv1C+j0B2l1ySUzRPp3LpgBdXn5R+yAMg6ix+G8EZXF5rLOt244cjR1xcCc4" +
                "kfEO2RoxrmES0cUgUX33xPJIbYUDMx4hTJlFAO2+eQ1M9VtuzgSG/hW+qr5Gp9q6" +
                "xd8ZrEsMdvfqd2QPOK0BY8nCLZZiDzYvZAjKGpWLTd/Owf8dF/a7HBN+hlGu9ygd" +
                "4e6NzQpcENWetybOQVw3v00F9Oscwn4hHcTuIzO3lK+rULEaxOuoVlp43NO7KzhV" +
                "eVg05eSggpEfu85RMiZw7iG+RpuxOAwiOPPIE8vZgishHYVB1J2sxRBxElS6Qj0I" +
                "KT2gxuT4e8gJ9YAn4CLWMGPxKDxn+tql0Lb2X6Q00jp/Iptk/dYveiQ7WZ4ijkde" +
                "Ug3f++d6H24Q/knrQxJJXttHFBthtJcI71i5Z+9zGmnZMBMolCZLFcaO/ZK5/yOg" +
                "xxyRid8PaBjT2t90odja7QyzHnP4J0lBp3xOuBXEGmn/gLoWv47VEGADlyJoXQSo" +
                "gThhaFhPbb7ttHI3ghFj6FrXF9zo0vr4dxwW1wur6b+pciKEpl8K+zIIayc4vpVe" +
                "TYjuOXTCB+VboISuLvlaVkdWR7dYaTy9Tma8IaBspqgb6/zoGoKAwc2iDP3Up+xr" +
                "RHOHmGlTjABD1uMlomFPlVMCAwEAAQ==";

        servicesProperties.setPublicKey(secret);
        signingKeyResolverMock = new JWTSigningKeyResolver(new PublicKeyRepo(servicesProperties, new StringKeyLoader()));
        sut = new TokenProvider(signingKeyResolverMock);

        boolean result = sut.validateToken(validToken);

        assertFalse("Should not be valid", result);
    }

    @Test
    public void testIfKeyValidShouldNotBeValid() {

        boolean result = sut.validateToken(validToken);

        assertTrue("Should be valid", result);
    }

    @Test
    public void testAuthenticateShouldReturnAuthentication() {

        Authentication authentication = sut.getAuthentication(validToken);

        assertNotNull("Should not be null", authentication.getAuthorities());
        assertFalse("Should not be empty", authentication.getAuthorities().isEmpty());
        assertEquals("Should not be equal", validToken, authentication.getCredentials());
        final User user = (User) authentication.getPrincipal();
        assertNotNull("Should not be null", user);
        assertEquals("Should be equal", "First Last", user.getFullName());
        assertEquals("Should be equal", "86152316253", user.getStaffNumber());
    }

}
