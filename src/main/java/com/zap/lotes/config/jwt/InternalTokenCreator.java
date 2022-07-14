package com.zap.lotes.config.jwt;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.zap.lotes.config.properties.SecurityProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Log4j2
@Service
@RequiredArgsConstructor
public class InternalTokenCreator {

    private static final String AUTH_ID = "USOINTERNO";
    private static final String USERNAME = "zapinternal";
    private static final String AUTH_ISSUER = "ContaZapApi";
    private static final String AUTHORITIES_KEY = "auth";
    private static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5 * 60;     // 5 minutos de validade
    private final SecurityProperties securityProperties;

    public String generateInternalToken() {
        try {
            JWSSigner signer = new MACSigner(securityProperties.getJwtKey().getBytes(StandardCharsets.UTF_8));
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .issuer(AUTH_ISSUER)
                    .subject(USERNAME)
                    .claim(AUTHORITIES_KEY, "ROLE_API")
                    .issueTime(new Date(System.currentTimeMillis()))
                    .notBeforeTime(new Date(System.currentTimeMillis()))
                    .expirationTime(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
                    .jwtID(AUTH_ID)
                    .build();
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS512), claimsSet);
            signedJWT.sign(signer);
            return signedJWT.serialize();
        } catch (Exception e) {
            log.error("Erro ao criar token interno", e);
            return null;
        }
    }

}
