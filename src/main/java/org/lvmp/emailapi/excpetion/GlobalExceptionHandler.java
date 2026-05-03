package org.lvmp.emailapi.excpetion;

import lombok.extern.slf4j.Slf4j;
import org.lvmp.emailapi.model.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String AN_ERROR_OCCURRED = "An error occurred with our service";
    private static final String SEND_EMAIL_ERROR = "An error occurred when sending the email";

    @ExceptionHandler(SendEmailException.class)
    public ResponseEntity<ErrorResponse> handleSendEmailException(SendEmailException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.unprocessableContent().body(ErrorResponse.builder().message(SEND_EMAIL_ERROR).description(e.getMessage()).build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(final RuntimeException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.internalServerError().body(ErrorResponse.builder().message(AN_ERROR_OCCURRED).description(e.getMessage()).build());
    }
}
