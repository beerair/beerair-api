package com.beerair.core.auth.infrastructure.jwt;

import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.beerair.core.auth.application.AuthTokenProvider;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

public abstract class JJwtProvider implements AuthTokenProvider {
    @Setter
    private JJwtProvider next;

    private final SignatureAlgorithm signatureAlgorithm;
    private final Key signatureKey;
    private final int expiration;
    private final JwtParser jwtParser;

    public JJwtProvider(String signatureAlgorithm, String signatureKey, int expiration) {
        this.signatureAlgorithm = SignatureAlgorithm.forName(signatureAlgorithm);
        this.signatureKey = new SecretKeySpec(
            DatatypeConverter.parseBase64Binary(signatureKey),
            this.signatureAlgorithm.getJcaName()
        );

        this.jwtParser = Jwts.parserBuilder()
                             .setSigningKey(this.signatureKey)
                             .build();
        this.expiration = expiration;
    }

    @Override
    public final String getId(String token) {
        return jwtParser.parseClaimsJws(token)
                        .getBody()
                        .getSubject();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(String token) {
        @SuppressWarnings("unchecked")
        List<String> authorities = jwtParser
            .parseClaimsJws(token)
            .getBody()
            .get(ClaimKey.AUTHORITIES, ArrayList.class);
        return authorities
            .stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toSet());
    }

    @SneakyThrows
    @Override
    public final String encode(Authentication authentication) {
        if (!isProvidable(authentication)) {
            return next.encode(authentication);
        }
        Date now = new Date();
        return Jwts.builder()
            .setSubject(getId(authentication))
            .claim(ClaimKey.AUTHORITIES, getAuthorities(authentication))
            .signWith(signatureKey, signatureAlgorithm)
            .setIssuedAt(new Date())
            .setExpiration(new Date(now.getTime() + expiration))
            .compact();
    }

    protected abstract boolean isProvidable(Authentication authentication);

    protected abstract String getId(Authentication authentication);

    protected abstract List<String> getAuthorities(Authentication authentication);

    @UtilityClass
    private static class ClaimKey {
        private final String AUTHORITIES = "authorities";
        private final String AUTHORITY = "authority";
    }
}
