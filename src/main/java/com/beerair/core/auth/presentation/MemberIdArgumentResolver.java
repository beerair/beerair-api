package com.beerair.core.auth.presentation;

import com.beerair.core.auth.domain.AuthTokenAuthentication;
import com.beerair.core.member.application.MemberId;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;
import java.util.Optional;

public class MemberIdArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Objects.nonNull(parameter.getParameterAnnotation(MemberId.class));
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {
        var memberId = memberId();
        if (parameter.getParameterType() == Optional.class) {
            return memberId;
        }
        return memberId.orElseThrow(() -> new RuntimeException("TODO"));
    }

    private Optional<String> memberId() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AuthTokenAuthentication) {
            return Optional.of((String) authentication.getPrincipal());
        }
        return Optional.empty();
    }
}
