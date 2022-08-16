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
        var errorMessage = e.getErrorMessage();
        
        log.error("[ERROR] BusinessException -> {}", errorMessage.getDescription());

        return ResponseEntity
                .status(errorMessage.getStatus())
                .body(new ErrorDto(errorMessage));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        var errorMessage = ErrorMessage.CONFLICT_ERROR;

        log.error("[ERROR] MethodArgumentNotValidException -> {}", e.getBindingResult());

        return ResponseEntity
                .status(errorMessage.getStatus())
                .body(new ErrorDto(errorMessage, e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorDto> handleException(final Exception e) {
        var errorMessage = ErrorMessage.CONFLICT_ERROR;

        log.error("[ERROR] Exception -> {}", e.getCause().toString());

        return ResponseEntity
                .status(errorMessage.getStatus())
                .body(new ErrorDto(errorMessage, e.getMessage()));
    }
}
