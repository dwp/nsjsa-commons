package uk.gov.dwp.jsa.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.gov.dwp.jsa.adaptors.ServicesProperties;

import java.security.PublicKey;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PublicKeyRepoTest {

    private static final String KEY_ID = "keyId";

    private static final String NO_PUBLIC_KEY_ASSOCIATED_KEY_ID = "noPublicKeyAssociatedKeyId";

    private static final String DUMMY_PUBLIC_KEY = "anyPublicKey";

    private static final String DEFAULT_PUBLIC_KEY = "defaultPublicKey";

    @Mock
    private ServicesProperties propMock;

    @Mock
    private KeyLoader<String> keyLoaderMock;

    @Mock
    private PublicKey publicKeyMock;

    @Mock
    private PublicKey defaultPublicKeyMock;

    private PublicKeyRepo testSubject;

    @Before
    public void setUp() {
        when(propMock.getPublicKey()).thenReturn(DEFAULT_PUBLIC_KEY);
        when(propMock.getPublicKeyMap()).thenReturn(Collections.singletonMap(KEY_ID, DUMMY_PUBLIC_KEY));
        when(keyLoaderMock.loadPublicKey(DUMMY_PUBLIC_KEY)).thenReturn(publicKeyMock);
        when(keyLoaderMock.loadPublicKey(DEFAULT_PUBLIC_KEY)).thenReturn(defaultPublicKeyMock);

        testSubject = new PublicKeyRepo(propMock, keyLoaderMock);
    }

    @Test
    public void test_getDefaultKey_shouldReturnDefaultKey() {
        assertEquals(defaultPublicKeyMock, testSubject.getDefaultKey());
    }

    @Test
    public void test_getKeyById_shouldReturnPublicKeyIfKIDExists() {
        assertEquals(publicKeyMock, testSubject.getKeyById(KEY_ID));
    }

    @Test
    public void test_getKeyById_shouldReturnNullIfKIDDoesNotExist() {
        assertNull(testSubject.getKeyById(NO_PUBLIC_KEY_ASSOCIATED_KEY_ID));
    }
}
