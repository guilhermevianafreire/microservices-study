package br.dev.gvf.productservice.controller;

import br.dev.gvf.productservice.exception.ValidationException;
import jakarta.persistence.EntityNotFoundException;
import java.util.NoSuchElementException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException exception) {
    return new ResponseEntity<>(
        ErrorResponse.builder(exception, HttpStatus.NOT_FOUND, exception.getMessage()).build()
            .updateAndGetBody(getMessageSource(), LocaleContextHolder.getLocale()),
        HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<Object> handleValidationException(ValidationException exception) {
    return new ResponseEntity<>(
        ErrorResponse.builder(exception, HttpStatus.BAD_REQUEST, exception.getDetail()).build()
            .updateAndGetBody(getMessageSource(), LocaleContextHolder.getLocale()),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException exception) {
    return new ResponseEntity<>(
        ErrorResponse.builder(exception, HttpStatus.BAD_REQUEST, exception.getMessage()).build()
            .updateAndGetBody(getMessageSource(), LocaleContextHolder.getLocale()),
        HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status,
      WebRequest request) {
    return new ResponseEntity<>(
        ErrorResponse.builder(exception, HttpStatus.BAD_REQUEST, exception.getMessage()).build()
            .updateAndGetBody(getMessageSource(), LocaleContextHolder.getLocale()),
        HttpStatus.BAD_REQUEST);
  }

}
