package uk.gov.dwp.jsa.adaptors;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import uk.gov.dwp.jsa.security.JWTFilter;
import uk.gov.dwp.jsa.security.roles.Role;

import java.util.Collections;

@Component
public class HttpEntityFactory {

    public HttpEntity createWithJsonHeaders() {
        HttpHeaders headers = createHeaders();
        return new HttpEntity<>("parameters", headers);
    }

    public HttpEntity createWithJsonHeaders(final Object payload) {
        HttpHeaders headers = createHeaders();
        return new HttpEntity<>(payload, headers);
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        Role.getJWT().ifPresent(jwt -> headers.add(JWTFilter.AUTHORIZATION_HEADER, JWTFilter.AUTH_PREFIX + jwt));
        Role.getPayload().ifPresent(tokenPayload -> headers.add(JWTFilter.TOKEN_PAYLOAD_HEADER, tokenPayload));
        return headers;
    }

}
