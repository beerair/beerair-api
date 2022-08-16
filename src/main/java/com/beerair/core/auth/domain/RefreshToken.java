package com.beerair.core.auth.domain;

import com.beerair.core.common.domain.BaseEntity;
import com.beerair.core.common.util.IdGenerator;
import com.beerair.core.error.exception.auth.ExpiredAuthTokenException;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import static com.beerair.core.common.util.IdGenerator.UUID_LENGTH;

@Getter
@Entity
public class RefreshToken extends BaseEntity {
    @Id
    @Column(nullable = false, length = UUID_LENGTH)
    private String id;

    @Column(nullable = false, length = UUID_LENGTH)
    private String memberId;

    @Column(nullable = false, length = 1000)
    private String token;

    @Column(nullable = false)
    private Boolean used;

    protected RefreshToken() {
    }

    public RefreshToken(String memberId, String token) {
        this.id = IdGenerator.createUUID();
        this.memberId = memberId;
        this.token = token;
        this.used = false;
    }

    public void use() {
        if (this.used) {
            throw new ExpiredAuthTokenException();
        }
        this.used = true;
    }
}
