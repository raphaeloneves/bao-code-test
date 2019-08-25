package pt.raphaelneves.bao.test.codingchallenge.configurations;

import feign.FeignException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pt.raphaelneves.bao.test.codingchallenge.dtos.ResponseErrorDto;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseErrorDto integrationError(FeignException feignException) {
        return ResponseErrorDto.builder()
                .timestamp(LocalDateTime.now())
                .rootCause(feignException.getMessage())
                .message("External service unavailable")
                .build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseErrorDto credentialsError(AccessDeniedException ade) {
        return ResponseErrorDto.builder()
                .timestamp(LocalDateTime.now())
                .rootCause(ade.getMessage())
                .message("Invalid credentials")
                .build();
    }
}
