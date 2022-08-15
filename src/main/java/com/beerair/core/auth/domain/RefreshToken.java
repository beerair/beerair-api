package com.beerair.core.auth.domain;

import com.beerair.core.common.domain.BaseEntity;
import com.beerair.core.common.util.IdGenerator;
import com.beerair.core.error.exception.auth.RefreshTokenAlreadyUsedException;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Table(indexes = {
        @Index(name = "INDEX_TOKEN", columnList = "token", unique = true)
})
@Getter
@Entity
public class RefreshToken extends BaseEntity {
    @Id
    private String id;

    @Column(unique = true)
    private String token;

    private Boolean used;

    protected RefreshToken() {
    }

    public RefreshToken(String token) {
        this.id = IdGenerator.createUUID();
        this.token = token;
        this.used = false;
    }

    public void use() {
        if (this.used) {
            throw new RefreshTokenAlreadyUsedException();
        }
        this.used = true;
    }
}
