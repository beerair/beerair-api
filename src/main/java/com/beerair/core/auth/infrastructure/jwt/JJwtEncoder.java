package com.beerair.core.auth.infrastructure.jwt;

import com.beerair.core.auth.domain.AuthTokenAuthentication;
import com.beerair.core.auth.domain.AuthTokenEncoder;
import com.beerair.core.auth.dto.response.CustomGrantedAuthority;
import com.beerair.core.common.util.MapperUtil;
import com.beerair.core.member.dto.LoggedInUser;
import com.fasterxml.jackson.core.type.TypeReference;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.GrantedAuthority;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

public final class JJwtEncoder implements AuthTokenEncoder {
    private final SignatureAlgorithm signatureAlgorithm;
    private final Key signatureKey;
    private final int expiration;
    private final JwtParser jwtParser;

    public JJwtEncoder(String signatureAlgorithm, String signatureKey, int expiration) {
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
    public LoggedInUser getLoggedInUser(String token) {
        String json = jwtParser
                .parseClaimsJws(token)
                .getBody()
                .get(ClaimKey.USER, String.class);
        return MapperUtil.readValue(json, TypeRef.USER);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(String token) {
        String json = jwtParser
                .parseClaimsJws(token)
                .getBody()
                .get(ClaimKey.AUTHORITIES, String.class);
        return MapperUtil.readValue(json, TypeRef.AUTHORITIES);
    }

    @Override
    public Date getExpired(String token) {
        return jwtParser.parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }

    @Override
    public String encode(AuthTokenAuthentication authentication) {
        return encode(authentication.getPrincipal(), authentication.getAuthorities());
    }

    @SneakyThrows
    @Override
    public String encode(LoggedInUser loggedInUser, Collection<? extends GrantedAuthority> authorities) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(loggedInUser.getId())
                .claim(ClaimKey.USER, MapperUtil.writeValueAsString(loggedInUser))
                .claim(ClaimKey.AUTHORITIES, MapperUtil.writeValueAsString(authorities))
                .signWith(signatureKey, signatureAlgorithm)
                .setIssuedAt(new Date())
                .setExpiration(new Date(now.getTime() + expiration))
                .compact();
    }

    @UtilityClass
    private static class ClaimKey {
        private final String AUTHORITIES = "authorities";
        private final String USER = "user";
    }

    @UtilityClass
    private static class TypeRef {
        private static final TypeReference<Set<CustomGrantedAuthority>> AUTHORITIES = new TypeReference<>() {};
        private static final TypeReference<LoggedInUser> USER = new TypeReference<>() {};
    }
}
