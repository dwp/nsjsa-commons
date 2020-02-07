package uk.gov.dwp.jsa.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final TokenProvider tokenProvider;

    private final Environment environment;

    public JWTConfigurer(final TokenProvider tokenProvider, final Environment environment) {
        this.tokenProvider = tokenProvider;
        this.environment = environment;
    }

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        JWTFilter customFilter = new JWTFilter(tokenProvider, environment, new ObjectMapper());
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);

        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }
}
