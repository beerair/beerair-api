package com.beerair.core.auth.presentation;

import com.beerair.core.auth.domain.AuthTokenAuthentication;
import com.beerair.core.error.exception.auth.NoAuthException;
import com.beerair.core.member.dto.LoggedInUser;
import com.beerair.core.member.presentation.annotation.AuthUser;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;
import java.util.Optional;

public class AuthUserArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Objects.nonNull(parameter.getParameterAnnotation(AuthUser.class));
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {
        var loggedInUser = loggedInUser();
        if (parameter.getParameterType() == Optional.class) {
            return loggedInUser;
        }
        return loggedInUser.orElseThrow(NoAuthException::new);
    }

    private Optional<LoggedInUser> loggedInUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AuthTokenAuthentication) {
            return Optional.of((LoggedInUser) authentication.getPrincipal());
        }
        return Optional.empty();
    }
}