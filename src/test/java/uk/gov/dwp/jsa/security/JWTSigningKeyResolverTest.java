package uk.gov.dwp.jsa.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.SigningKeyResolverAdapter;
import io.jsonwebtoken.impl.DefaultJwsHeader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.security.Key;
import java.security.PublicKey;
import java.util.Collections;

import static io.jsonwebtoken.JwsHeader.KEY_ID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JWTSigningKeyResolverTest {

    private static final String VALID_KEY_ID = "validKey";

    private static final String NO_PUBLIC_KEY_ASSOCIATED_KEY_ID = "noPublicKeyAssociated";

    @Mock
    private PublicKeyRepo publicKeyRepoMock;

    @Mock
    private PublicKey publicKeyMock;

    @Mock
    private PublicKey defaultPublicKeyMock;

    private static final JwsHeader JWS_HEADER =
            new DefaultJwsHeader(Collections.singletonMap(KEY_ID, VALID_KEY_ID));

    private static final JwsHeader JWS_HEADER_NO_KID = new DefaultJwsHeader();

    private static final JwsHeader JWS_HEADER_NO_PUBLIC_ASS =
            new DefaultJwsHeader(Collections.singletonMap(KEY_ID, NO_PUBLIC_KEY_ASSOCIATED_KEY_ID));

    private SigningKeyResolverAdapter testSubject;

    @Before
    public void setUp() {
        when(publicKeyRepoMock.getKeyById(VALID_KEY_ID)).thenReturn(publicKeyMock);
        when(publicKeyRepoMock.getDefaultKey()).thenReturn(defaultPublicKeyMock);
        testSubject = new JWTSigningKeyResolver(publicKeyRepoMock);
    }

    @Test
    public void test_resolveSigningKey_ShouldReturnThePublicKeyAssociatedToTheKeyId() {
        assertEquals(publicKeyMock, resolveSigningKey(JWS_HEADER));
    }

    @Test
    public void test_resolveSigningKey_ShouldReturnNullIfTheKIDDoesNotHavePublicKeyAssociated() {
        assertNull("Resolver should return null if kid is set in the header but the public does not exist in the repository.",
                resolveSigningKey(JWS_HEADER_NO_PUBLIC_ASS));
    }

    @Test
    public void test_resolveSigningKey_ShouldReturnDefaultPublicKeyIfTheKIDNotExistInHeader() {
        assertEquals("Resolver should return if kid is set in the header but the public does not exist in the repository.",
                defaultPublicKeyMock, resolveSigningKey(JWS_HEADER_NO_KID));
    }

    private Key resolveSigningKey(final JwsHeader jwsHeader) {
        return testSubject.resolveSigningKey(jwsHeader, (Claims) null);
    }
}
