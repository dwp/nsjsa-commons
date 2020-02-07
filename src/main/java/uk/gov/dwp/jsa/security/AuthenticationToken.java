package uk.gov.dwp.jsa.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthenticationToken extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = -4980756218132214506L;

    public AuthenticationToken(final String fullName, final String staffNumber, final String token,
                               final Collection<? extends GrantedAuthority> authorities) {
        this(fullName, staffNumber, token, authorities, null);
    }

    public AuthenticationToken(final String fullName, final String staffNumber, final String token,
                               final Collection<? extends GrantedAuthority> authorities, final String payload) {
        super(new User(fullName, staffNumber, payload), token, authorities);
    }
}
