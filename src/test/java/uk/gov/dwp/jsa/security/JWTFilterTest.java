package uk.gov.dwp.jsa.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static uk.gov.dwp.jsa.security.Constants.NoSecure.NO_SECURE_PROFILE;
import static uk.gov.dwp.jsa.security.JWTFilter.AUTH_PREFIX;

@RunWith(SpringJUnit4ClassRunner.class)
public class JWTFilterTest {

    @Mock
    private TokenProvider tokenProvider;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Environment environment;

    @Mock
    private FilterChain filterChain;

    private JWTFilter sut;

    @Before
    public void setUp() {
        when(environment.getActiveProfiles()).thenReturn(new String[]{"test"});
        sut = new JWTFilter(tokenProvider, environment, new ObjectMapper());
    }

    @Test
    public void testJwtIsEmptyShouldNotAuthenticate() throws IOException, ServletException {
        when(request.getHeader(JWTFilter.AUTHORIZATION_HEADER)).thenReturn(StringUtils.EMPTY);

        sut.doFilter(request, response, filterChain);

        verify(tokenProvider, never()).getAuthentication(anyString());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void testJwtWithNoSecureProfile() throws IOException, ServletException {
        final String token = "{\n" +
                "   \"sub\" : \"joe.bloggs@dwp.gsi.gov.uk\",\n" +
                "   \"aud\" : \"test,manager,base\",\n" +
                "   \"iss\" : \"dwp-adfs\",\n" +
                "   \"sid\" : \"5f1dd760-b1d0-78c9-9fdb-dbec3f1311b8\",\n" +
                "   \"iat\" : 1525250181,\n" +
                "   \"exp\" : 1525278981,\n" +
                "   \"username\" : \"00000221\"\n" +
                "}";
        when(request.getHeader(JWTFilter.AUTHORIZATION_HEADER)).thenReturn(AUTH_PREFIX + token);
        when(request.getHeader("tokenpayload")).thenReturn(Base64.encodeBase64String(token.getBytes()));
        when(tokenProvider.validateToken(token)).thenReturn(true);

        when(environment.getActiveProfiles()).thenReturn(new String[]{"test",NO_SECURE_PROFILE});
        when(request.getHeader(JWTFilter.AUTHORIZATION_HEADER)).thenReturn(StringUtils.EMPTY);

        sut.doFilter(request, response, filterChain);

        verify(tokenProvider, never()).getAuthentication(anyString());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void testHeaderPrefixNotCorrectShouldNotAuthenticate() throws IOException, ServletException {
        when(request.getHeader(JWTFilter.AUTHORIZATION_HEADER)).thenReturn("asdqwelhqlrha");

        sut.doFilter(request, response, filterChain);

        verify(tokenProvider, never()).getAuthentication(anyString());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void testJwtNotValidShouldNotAuthenticate() throws IOException, ServletException {
        final String token = "asdqwelhqlrha";
        when(request.getHeader(JWTFilter.AUTHORIZATION_HEADER)).thenReturn(AUTH_PREFIX + token);
        when(tokenProvider.validateToken(token)).thenReturn(false);

        sut.doFilter(request, response, filterChain);

        verify(tokenProvider, never()).getAuthentication(anyString());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void testJwtValidShouldAuthenticate() throws IOException, ServletException {
        final String token = "asdqwelhqlrha";
        when(request.getHeader(JWTFilter.AUTHORIZATION_HEADER)).thenReturn(AUTH_PREFIX + token);
        when(tokenProvider.validateToken(token)).thenReturn(true);

        sut.doFilter(request, response, filterChain);

        verify(tokenProvider).getAuthentication(anyString());
        verify(filterChain).doFilter(request, response);
    }
}
