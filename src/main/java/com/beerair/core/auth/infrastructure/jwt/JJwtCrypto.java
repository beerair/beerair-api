package com.beerair.core.auth.infrastructure.jwt;

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
import lombok.Builder;
import lombok.experimental.UtilityClass;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Set;

public class JJwtCrypto implements AuthTokenCrypto {
    private final SignatureAlgorithm signatureAlgorithm;
    private final Key signatureKey;
    private final int expiration;
    private final JwtParser jwtParser;
    private final String tokenPurpose;

    @Builder
    private JJwtCrypto(String tokenPurpose, String signatureAlgorithm, String signatureKey, int expiration) {
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
    public String encrypt(AuthTokenAuthentication authentication) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(authentication.getLoggedInUser().getId())
                .compressWith(CompressionCodecs.GZIP)
                .claim(ClaimKey.PURPOSE, tokenPurpose)
                .claim(ClaimKey.USER, MapperUtil.writeValueAsString(authentication.getLoggedInUser()))
                .claim(ClaimKey.AUTHORITIES, MapperUtil.writeValueAsString(authentication.getAuthorities()))
                .signWith(signatureKey, signatureAlgorithm)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expiration))
                .compact();
    }

    @Override
    public AuthTokenAuthentication decrypt(String token) {
        try {
            Claims body = jwtParser
                    .parseClaimsJws(token)
                    .getBody();
            if (!isNotMatchPurpose(tokenPurpose)) {
                throw new InvalidAuthTokenException();
            }
            return convert(body);
        } catch(ExpiredJwtException e) {
            throw new ExpiredAuthTokenException();
        } catch (JwtException e) {
            throw new InvalidAuthTokenException();
        }
    }

    private boolean isNotMatchPurpose(String tokenPurpose) {
        return this.tokenPurpose.equals(tokenPurpose);
    }

    public AuthTokenAuthentication convert(Claims body) {
        LoggedInMember loggedInUser = MapperUtil.readValue(
                body.get(ClaimKey.USER, String.class),
                TypeRef.USER
        );
        Set<CustomGrantedAuthority> authorities = MapperUtil.readValue(
                body.get(ClaimKey.AUTHORITIES, String.class),
                TypeRef.AUTHORITIES
        );
        return AuthTokenAuthentication.from(loggedInUser, authorities, body.getExpiration());
    }


    @UtilityClass
    private static class ClaimKey {
        private final String PURPOSE = "purpose";
        private final String AUTHORITIES = "authorities";
        private final String USER = "user";
    }

    @UtilityClass
    private static class TypeRef {
        private static final TypeReference<Set<CustomGrantedAuthority>> AUTHORITIES = new TypeReference<>() {};
        private static final TypeReference<LoggedInMember> USER = new TypeReference<>() {};
    }
}
