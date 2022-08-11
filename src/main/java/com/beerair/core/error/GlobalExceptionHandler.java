package com.beerair.core.error;

import com.beerair.core.error.dto.ErrorDto;
import com.beerair.core.error.dto.ErrorMessage;
import com.beerair.core.error.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorDto> handleBusinessException(final BusinessException e) {
        log.error("[ERROR] BusinessException -> {}", "Business Exception");

        return ResponseEntity.status(e.getErrorMessage().getStatus())
                .body(new ErrorDto(e.getErrorMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        log.error("[ERROR] MethodArgumentNotValidException -> {}", e.getBindingResult());

        var errorMessage = ErrorMessage.CONFLICT_ERROR;

        return ResponseEntity.status(errorMessage.getStatus())
                .body(new ErrorDto(errorMessage, e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorDto> handleException(final Exception e) {
        log.error("[ERROR] Exception -> {}", e.getCause().toString());

        var errorMessage = ErrorMessage.CONFLICT_ERROR;

        return ResponseEntity.status(errorMessage.getStatus())
                .body(new ErrorDto(errorMessage, e.getMessage()));
    }
}
