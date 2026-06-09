package com.amalvadkar.ums.common.exceptions;

import com.amalvadkar.ums.common.model.response.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomResponse> handleValidationException(MethodArgumentNotValidException ex) {
        logException(ex);
        return ResponseEntity.badRequest()
                .body(CustomResponse.badRequest(extractValidationErrorMessages(ex)));
    }

    @ExceptionHandler(PermissionDeniedException.class)
    public ResponseEntity<CustomResponse> handlePermissionDeniedException(PermissionDeniedException ex) {
        logException(ex);
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(CustomResponse.permissionDenied(ex.getMessage()));
    }

    private static void logException(Throwable ex) {
        log.error("Exception occurred :: ", ex);
    }

    private static List<String> extractValidationErrorMessages(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
    }

}
