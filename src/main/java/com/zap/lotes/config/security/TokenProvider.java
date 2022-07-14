package com.zap.lotes.config.security;

import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.zap.lotes.config.properties.SecurityProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenProvider {

    private static final String AUTHORITIES_KEY = "auth";
    private final SecurityProperties securityProperties;

    public Authentication getAuthentication(String token) {
        try {
            token = extractToken(token);
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(securityProperties.getJwtKey().getBytes(StandardCharsets.UTF_8));
            if (!signedJWT.verify(verifier)) {
                return null;
            }
            JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
            Collection<? extends GrantedAuthority> authorities =
                    Arrays.stream(claims.getClaim(AUTHORITIES_KEY).toString().split(","))
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());
            User principal = new User(claims.getSubject(), "", authorities);
            return new UsernamePasswordAuthenticationToken(principal, token, authorities);
        } catch (Exception e) {
            log.error("Token inválido", e);
            return null;
        }
    }

    public boolean validateToken(String authToken) {
        try {
            log.trace("Token -> " + authToken);
            log.trace("Horário Atual: {}", LocalDateTime.now());
            authToken = extractToken(authToken);
            SignedJWT signedJWT = SignedJWT.parse(authToken);
            JWSVerifier verifier = new MACVerifier(securityProperties.getJwtKey().getBytes(StandardCharsets.UTF_8));
            if (!signedJWT.verify(verifier)) {
                return false;
            }
            JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
            return !Instant.ofEpochMilli(claims.getExpirationTime().getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate().isBefore(ChronoLocalDate.from(LocalDateTime.now()));
        } catch (Exception e) {
            log.info("Invalid JWT token.");
            log.trace("Invalid JWT token trace.", e);
        }
        return false;
    }

    private String extractToken(String authToken) {
        if (authToken.toLowerCase().startsWith("bearer"))
            return authToken.substring("bearer ".length());
        return authToken;
    }

}
