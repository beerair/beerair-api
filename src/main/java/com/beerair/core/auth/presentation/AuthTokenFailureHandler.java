package com.beerair.core.auth.presentation;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@Component
public class AuthTokenFailureHandler implements AuthenticationFailureHandler {
    private static final String WRITER_CONTENT_TYPE = "text/html; charset=UTF-8;";
    private static final String WARING_TEMPLATE =
            "<script>" +
                    "alert('%s');" +
                    "history.back();" +
            "</script>";
    private static final int BASIC_STATUS_CODE = 500;
    private static final String GLOBAL_EXCEPTION_MESSAGE = "예기치 못한 에러가 발생했습니다.";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        if (exception instanceof OAuth2AuthenticationException) {
            handle(response, (OAuth2AuthenticationException) exception);
        }
        printWaring(response, BASIC_STATUS_CODE, GLOBAL_EXCEPTION_MESSAGE);
    }

    private void handle(HttpServletResponse response, OAuth2AuthenticationException exception) throws IOException {
        var error = exception.getError();
        var message = error.getDescription();
        int status = BASIC_STATUS_CODE;
        try {
            status = Integer.parseInt(error.getErrorCode());
        } catch (NumberFormatException ignored) {}

        printWaring(response, status, message);
    }

    private void printWaring(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        response.setContentType(WRITER_CONTENT_TYPE);
        PrintWriter out = response.getWriter();

        String printableMessage = Objects.requireNonNullElse(
                message, GLOBAL_EXCEPTION_MESSAGE
        );
        out.println(String.format(WARING_TEMPLATE, printableMessage));
        out.flush();
    }
}
