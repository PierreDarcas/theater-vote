package com.theater.app.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String SCOPE_READ_ALL = "read:all";

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/play").permitAll()
                .mvcMatchers("/play/id").permitAll()
                .mvcMatchers("/play/create").authenticated()
                .mvcMatchers("/play/delete/*").authenticated()
                .mvcMatchers("/play/update/*").authenticated()
                .mvcMatchers("/review").permitAll()
                .mvcMatchers("/review/id").permitAll()
                .mvcMatchers("/review/viewer").permitAll()
                .mvcMatchers("/review/play").permitAll()
                .mvcMatchers("/review/create").permitAll()
                .mvcMatchers("/review/update/*").authenticated()
                .mvcMatchers("/review/delete/*").authenticated()
                .and().cors()
                .and().oauth2ResourceServer().jwt();

    }

    @Value("${auth0.audience}")
    private String audience;

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuer;

    @Bean
    JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder)
                JwtDecoders.fromOidcIssuerLocation(issuer);

        OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(audience);
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);

        jwtDecoder.setJwtValidator(withAudience);

        return jwtDecoder;
    }
}