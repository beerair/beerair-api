package com.beerair.core.common.util;

import lombok.experimental.UtilityClass;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;

@UtilityClass
public class ValidateUtil {
    private static final Validator VALIDATOR;

    static {
        var factory = Validation.buildDefaultValidatorFactory();
        VALIDATOR = factory.getValidator();
    }

    public static <T> void validate(T object, Class<?>... groups) {
        var violations = VALIDATOR.validate(object, groups);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
