package com.beerair.core.auth.infrastructure.jwt;

import java.security.Key;
import java.util.Collection;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.beerair.core.auth.domain.AuthTokenProvider;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Setter;
import lombok.experimental.UtilityClass;

public abstract class JJwtProvider implements AuthTokenProvider {
    @Setter
    private JJwtProvider next;

    private SignatureAlgorithm signatureAlgorithm;
    private Key signatureKey;
    private int expiration;
    private JwtParser jwtParser;

    public JJwtProvider(String signatureAlgorithm, String signatureKey, int expiration) {
        this.signatureAlgorithm = SignatureAlgorithm.forName(signatureAlgorithm);
        this.signatureKey = new SecretKeySpec(
            DatatypeConverter.parseBase64Binary(signatureKey),
            signatureAlgorithm
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
        //noinspection unchecked
        return (Collection<? extends GrantedAuthority>) jwtParser.parseClaimsJws(token)
                        .getBody()
                        .get(ClaimKey.AUTHORITIES);
    }

    @Override
    public final String encode(Authentication authentication) {
        if (!isProvidable(authentication)) {
            return next.encode(authentication);
        }
        return Jwts.builder()
            .setSubject(getId(authentication))
            .claim(ClaimKey.AUTHORITIES, getAuthorities(authentication))
            .setExpiration(createExpiration())
            .signWith(signatureKey, signatureAlgorithm)
            .compact();
    }

    private Date createExpiration() {
        Date now = new Date();
        return new Date(now.getTime() + expiration);
    }

    protected abstract boolean isProvidable(Authentication authentication);

    protected abstract String getId(Authentication authentication);

    protected abstract Collection<? extends GrantedAuthority> getAuthorities(Authentication authentication);

    @UtilityClass
    private static class ClaimKey {
        private final String AUTHORITIES = "authorities";
    }
}
