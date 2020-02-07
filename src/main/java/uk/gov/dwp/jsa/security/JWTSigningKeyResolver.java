package uk.gov.dwp.jsa.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.SigningKeyResolverAdapter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;

@Component
public class JWTSigningKeyResolver extends SigningKeyResolverAdapter {

    private final PublicKeyRepo publicKeyRepo;

    public JWTSigningKeyResolver(final PublicKeyRepo pPublicKeyRepo) {
        this.publicKeyRepo = pPublicKeyRepo;
    }

    @Override
    public Key resolveSigningKey(final JwsHeader jwsHeader,
                                 final Claims claims) {
        if (StringUtils.isEmpty(jwsHeader.getKeyId())) {
            return publicKeyRepo.getDefaultKey();
        } else {
            return publicKeyRepo.getKeyById(jwsHeader.getKeyId());
        }
    }
}
