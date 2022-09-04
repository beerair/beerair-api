package com.beerair.core.auth.presentation;

import com.beerair.core.error.dto.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@Slf4j
public class AuthTokenFailureHandler implements AuthenticationFailureHandler {
    private static final String WRITER_CONTENT_TYPE = "text/html; charset=UTF-8;";
    private static final String WARING_TEMPLATE =
            "<script>" +
                    "alert('%s');" +
                    "location.replace('%s');" +
                    "</script>";

    private final String redirectUrl;

    public AuthTokenFailureHandler(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception
    ) throws IOException {
        log.error("[ERROR] AuthenticationException -> {}", exception.getMessage());
        printWaring(response, exception.getMessage());
    }

    private void printWaring(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType(WRITER_CONTENT_TYPE);

        PrintWriter out = response.getWriter();
        out.println(String.format(WARING_TEMPLATE, printableMessage(message), redirectUrl));
        out.flush();
    }

    private String printableMessage(String message) {
        if (Objects.isNull(message)) {
            return ErrorMessage.BAD_LOGIN_REQUEST.getDescription();
        }
        return message;
    }
}
