package com.beerair.core.auth.infrastructure.jwt;

import com.beerair.core.auth.domain.AuthToken;
import com.beerair.core.auth.domain.AuthTokenAuthentication;
import com.beerair.core.auth.domain.AuthTokenCrypto;
import com.beerair.core.auth.dto.response.CustomGrantedAuthority;
import com.beerair.core.common.util.MapperUtil;
import com.beerair.core.error.exception.auth.ExpiredAuthTokenException;
import com.beerair.core.error.exception.auth.InvalidAuthTokenException;
import com.beerair.core.member.dto.LoggedInMember;
import com.fasterxml.jackson.core.type.TypeReference;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import java.util.Set;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import lombok.Builder;
import lombok.experimental.UtilityClass;

public class JwtCrypto implements AuthTokenCrypto {
    private final SignatureAlgorithm signatureAlgorithm;
    private final Key signatureKey;
    private final long expiration;
    private final JwtParser jwtParser;
    private final String tokenPurpose;

    @Builder
    private JwtCrypto(String tokenPurpose, String signatureAlgorithm, String signatureKey, long expiration) {
        this.tokenPurpose = tokenPurpose;
        this.signatureAlgorithm = SignatureAlgorithm.forName(signatureAlgorithm);
        this.signatureKey = new SecretKeySpec(
                DatatypeConverter.parseBase64Binary(signatureKey),
                this.signatureAlgorithm.getJcaName()
        );
        this.expiration = expiration;

        this.jwtParser = Jwts.parserBuilder()
                .setSigningKey(this.signatureKey)
                .build();
    }

    @Override
    public AuthToken encrypt(AuthTokenAuthentication authentication) {
        var now = new Date();
        var expired = new Date(now.getTime() + this.expiration);
        String token = Jwts.builder()
                .setSubject(authentication.getLoggedInUser().getId())
                .compressWith(CompressionCodecs.GZIP)
                .claim(ClaimKey.PURPOSE, tokenPurpose)
                .claim(ClaimKey.USER, MapperUtil.writeValueAsString(authentication.getLoggedInUser()))
                .claim(ClaimKey.AUTHORITIES, MapperUtil.writeValueAsString(authentication.getAuthorities()))
                .signWith(signatureKey, signatureAlgorithm)
                .setIssuedAt(now)
                .setExpiration(expired)
                .compact();
        return new AuthToken(token, expired);
    }

    @Override
    public AuthTokenAuthentication decrypt(String token) {
        try {
            var body = jwtParser
                    .parseClaimsJws(token)
                    .getBody();
            if (!isNotMatchPurpose(tokenPurpose)) {
                throw new InvalidAuthTokenException();
            }
            return convert(body);
        } catch (ExpiredJwtException e) {
            throw new ExpiredAuthTokenException();
        } catch (JwtException e) {
            throw new InvalidAuthTokenException();
        }
    }

    private boolean isNotMatchPurpose(String tokenPurpose) {
        return this.tokenPurpose.equals(tokenPurpose);
    }

    public AuthTokenAuthentication convert(Claims body) {
        var loggedInUser = MapperUtil.readValue(
                body.get(ClaimKey.USER, String.class),
                TypeRef.USER
        );

        var authorities = MapperUtil.readValue(
                body.get(ClaimKey.AUTHORITIES, String.class),
                TypeRef.AUTHORITIES
        );

        return AuthTokenAuthentication.from(loggedInUser, authorities);
    }


    @UtilityClass
    private static class ClaimKey {
        private final String PURPOSE = "purpose";
        private final String AUTHORITIES = "authorities";
        private final String USER = "user";
    }

    @UtilityClass
    private static class TypeRef {
        private static final TypeReference<Set<CustomGrantedAuthority>> AUTHORITIES = new TypeReference<>() {
        };
        private static final TypeReference<LoggedInMember> USER = new TypeReference<>() {
        };
    }
}
