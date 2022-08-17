package com.beerair.core.common.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@RequiredArgsConstructor
@Converter
public class StringFieldCryptConverter implements AttributeConverter<String, String> {
    private final FieldCrypto crypto;

    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (Objects.isNull(attribute)) {
            return null;
        }
        return crypto.encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        if (Objects.isNull(dbData)) {
            return null;
        }
        return crypto.decrypt(dbData);
    }
}
