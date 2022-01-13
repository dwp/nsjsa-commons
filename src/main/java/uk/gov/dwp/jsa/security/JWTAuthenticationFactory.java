package uk.gov.dwp.jsa.security;

import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class JWTAuthenticationFactory {

    private TokenProvider tokenProvider;

    @Autowired
    public JWTAuthenticationFactory(
            final TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    public Authentication create(
            final PrivateKey privateKey,
            final String serviceName,
            final String roleName) {
        final Map<String, Object> jwtValues =
                new HashMap<String, Object>();
        jwtValues.put("sid", UUID.randomUUID().toString());
        jwtValues.put("iat", String.valueOf(Instant.now().toEpochMilli()));
        jwtValues.put("sub", UUID.randomUUID().toString());
        jwtValues.put("iss", serviceName);
        jwtValues.put("aud", roleName);
        jwtValues.put("username", "");

        final String jwt = Jwts.builder()
                .setHeaderParam(JwsHeader.KEY_ID, serviceName)
                .setHeaderParam(JwsHeader.TYPE, "JWT")
                .setClaims(jwtValues)
                .signWith(privateKey)
                .compact();

        return this.tokenProvider.getAuthentication(jwt);
    }

}
