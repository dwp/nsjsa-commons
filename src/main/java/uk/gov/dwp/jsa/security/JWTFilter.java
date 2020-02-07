package uk.gov.dwp.jsa.security;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import uk.gov.dwp.jsa.adaptors.RestfulExecutor;
import uk.gov.dwp.jsa.security.roles.Role;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static uk.gov.dwp.jsa.security.Constants.NoSecure.NO_SECURE_PROFILE;

public class JWTFilter extends GenericFilterBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestfulExecutor.class);

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String AUTH_PREFIX = "Token ";
    public static final String TOKEN_PAYLOAD_HEADER = "tokenpayload";
    public static final int START_OF_JWT = 6;

    private static final TypeReference<Map<String, Object>> STRING_TO_MAP
            = new TypeReference<Map<String, Object>>() { };

    private final ObjectMapper mapper;


    private TokenProvider tokenProvider;
    private final Environment environment;

    public JWTFilter(final TokenProvider tokenProvider, final Environment environment, final ObjectMapper pMapper) {
        this.tokenProvider = tokenProvider;
        this.environment = environment;
        this.mapper = pMapper;
    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
                         final FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String jwt = resolveToken(httpServletRequest);
        if (Arrays.asList(environment.getActiveProfiles()).contains(NO_SECURE_PROFILE)) {
            List<SimpleGrantedAuthority> authorities = setAuthoritiesForGivenProfiles();

            Authentication authentication = new AuthenticationToken("Mr John Smith", "12345678", "", authorities);
            String tokenPayload = resolveTokenPayload(httpServletRequest);
            if (StringUtils.hasText(tokenPayload)) {
                LOGGER.debug("Token payload present in the request.");
                Map<String, Object> payloadMap = mapper.readValue(
                        Base64.getDecoder().decode(tokenPayload), STRING_TO_MAP);
                Claims claims = Jwts.claims(payloadMap);
                authorities = Arrays.asList(claims.getAudience().split(","))
                                .stream()
                                .map(Role::fromGroup)
                                .filter(Optional::isPresent)
                                .map(Optional::get)
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList());
                LOGGER.debug("Token payload: " + tokenPayload);
                authentication = new AuthenticationToken(String.valueOf(payloadMap.get("username")),
                        String.valueOf(payloadMap.get("iat")), "", authorities, tokenPayload);
            }

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } else {
            if (StringUtils.hasText(jwt) && this.tokenProvider.validateToken(jwt)) {
                Authentication authentication = this.tokenProvider.getAuthentication(jwt);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private List<SimpleGrantedAuthority> setAuthoritiesForGivenProfiles() {
        final List<String> roles = Stream.of(Role.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        return Arrays.stream(environment.getActiveProfiles())
                .filter(roles::contains)
                .map(profile -> new SimpleGrantedAuthority(
                        Role.valueOf(profile).name())
                ).collect(Collectors.toList());

    }


    private String resolveToken(final HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(AUTH_PREFIX)) {
            return bearerToken.substring(START_OF_JWT);
        }
        return null;
    }

    private String resolveTokenPayload(final HttpServletRequest request) {
        return request.getHeader(TOKEN_PAYLOAD_HEADER);
    }
}
