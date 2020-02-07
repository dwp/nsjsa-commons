package uk.gov.dwp.jsa.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SigningKeyResolverAdapter;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import uk.gov.dwp.jsa.security.roles.Role;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Component
public class TokenProvider {

    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

    private static final String FULL_NAME_KEY = "username";
    private static final String STAFF_NUMBER_KEY = "sub";

    private final SigningKeyResolverAdapter keyResolver;

    public TokenProvider(final SigningKeyResolverAdapter pKeyResolver) {
        this.keyResolver = pKeyResolver;
    }

    Authentication getAuthentication(final String token) {
        Claims claims = Jwts.parser()
                .setSigningKeyResolver(keyResolver)
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.getAudience().split(","))
                        .map(Role::fromGroup)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .map(SimpleGrantedAuthority::new)
                        .collect(toList());

        return new AuthenticationToken(claims.get(FULL_NAME_KEY, String.class),
                claims.get(STAFF_NUMBER_KEY, String.class), token, authorities);
    }

    boolean validateToken(final String authToken) {
        try {
            Jwts.parser().setSigningKeyResolver(keyResolver).parseClaimsJws(authToken);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.error("Invalid JWT signature trace: {}", e);
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token trace: {}", e);
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token trace: {}", e);
        } catch (IllegalArgumentException e) {
            log.error("JWT token compact of handler are invalid trace: {}", e);
        }
        return false;
    }

}
