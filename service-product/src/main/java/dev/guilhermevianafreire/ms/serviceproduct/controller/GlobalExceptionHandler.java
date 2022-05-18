package dev.guilhermevianafreire.ms.serviceproduct.controller;

import dev.guilhermevianafreire.ms.serviceproduct.bundle.MessageBundlesHelper;
import dev.guilhermevianafreire.ms.serviceproduct.constant.ErrorMessageCode;
import dev.guilhermevianafreire.ms.shared.dto.error.ErrorDTO;
import dev.guilhermevianafreire.ms.shared.dto.error.ErrorDetailDTO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageBundlesHelper messageBundlesHelper;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetailDTO errorDetailDTO = ErrorDetailDTO
                .builder()
                .cause(ExceptionUtils.getRootCauseMessage(ex))
                .build();
        ErrorDTO errorDTO = ErrorDTO
                .builder()
                .errorCode(ErrorMessageCode.HTTP_MESSAGE_NOT_READABLE.name())
                .errorMessage(messageBundlesHelper.getMessage("http.message.not.readable"))
                .status(status)
                .path(request.getDescription(false))
                .errorDetails(Collections.singletonList(errorDetailDTO))
                .build();
        return handleExceptionInternal(ex, errorDTO, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ErrorDetailDTO> errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> ErrorDetailDTO
                        .builder()
                        .cause(MessageFormat.format("{0}: {1}", fieldError.getField(), fieldError.getDefaultMessage()))
                        .stackTraceAbbreviated(ex, 200)
                        .build()).toList();
        ErrorDTO errorDTO = ErrorDTO
                .builder()
                .errorCode(ErrorMessageCode.METHOD_ARGUMENT_NOT_VALID.name())
                .errorMessage(messageBundlesHelper.getMessage("method.argument.not.valid"))
                .status(status)
                .path(request.getDescription(false))
                .errorDetails(errorMessages)
                .build();
        return handleExceptionInternal(ex, errorDTO, headers, status, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDTO> validationErrorHandler(ConstraintViolationException ex, WebRequest request) {
        List<ErrorDetailDTO> errorMessages = ex.getConstraintViolations()
                .stream()
                .map(constraintViolation -> ErrorDetailDTO
                        .builder()
                        .cause(MessageFormat.format("{0} -> {1}: {2}", constraintViolation.getRootBeanClass().getName(), constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage()))
                        .build())
                .toList();
        ErrorDTO errorDTO = ErrorDTO
                .builder()
                .errorCode(ErrorMessageCode.BEAN_VALIDATION_CONSTRAINT_VIOLATION.name())
                .errorMessage(messageBundlesHelper.getMessage("bean.validation.constraint.violation"))
                .status(HttpStatus.BAD_REQUEST)
                .path(request.getDescription(false))
                .errorDetails(errorMessages)
                .build();
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        ErrorDetailDTO errorDetailDTO = ErrorDetailDTO
                .builder()
                .cause(ExceptionUtils.getRootCauseMessage(ex))
                .stackTraceAbbreviated(ex, 200)
                .build();
        ErrorDTO errorDTO = ErrorDTO
                .builder()
                .errorCode(ErrorMessageCode.DATA_INTEGRITY_VIOLATION.name())
                .errorMessage(messageBundlesHelper.getMessage("data.integrity.violation"))
                .status(HttpStatus.BAD_REQUEST)
                .path(request.getDescription(false))
                .errorDetail(errorDetailDTO)
                .build();
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        ErrorDetailDTO errorDetailDTO = ErrorDetailDTO
                .builder()
                .cause(ExceptionUtils.getRootCauseMessage(ex))
                .stackTraceAbbreviated(ex, 200)
                .build();
        ErrorDTO errorDTO = ErrorDTO
                .builder()
                .errorCode(ErrorMessageCode.ENTITY_NOT_FOUND_EXCEPTION.name())
                .errorMessage(messageBundlesHelper.getMessage("entity.not.found"))
                .status(HttpStatus.BAD_REQUEST)
                .path(request.getDescription(false))
                .errorDetail(errorDetailDTO)
                .build();
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
