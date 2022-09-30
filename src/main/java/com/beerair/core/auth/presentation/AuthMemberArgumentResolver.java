package com.beerair.core.auth.presentation;

import com.beerair.core.auth.domain.AuthTokenAuthentication;
import com.beerair.core.auth.presentation.filter.GetAuthenticationStrategy;
import com.beerair.core.error.exception.auth.NoAuthException;
import com.beerair.core.member.dto.LoggedInMember;
import com.beerair.core.member.presentation.annotation.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class AuthMemberArgumentResolver implements HandlerMethodArgumentResolver {
    private final GetAuthenticationStrategy getAuthenticationStrategy;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Objects.nonNull(parameter.getParameterAnnotation(AuthMember.class));
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {
        var httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        var loggedInUser = loggedInUser(httpServletRequest);
        if (parameter.getParameterType() == Optional.class) {
            return loggedInUser;
        }
        return loggedInUser.orElseThrow(NoAuthException::new);
    }

    private Optional<LoggedInMember> loggedInUser(HttpServletRequest request) {
        var authentication = getAuthenticationStrategy.get(request);
        if (authentication instanceof AuthTokenAuthentication) {
            return Optional.of((LoggedInMember) authentication.getPrincipal());
        }
        return Optional.empty();
    }
}
