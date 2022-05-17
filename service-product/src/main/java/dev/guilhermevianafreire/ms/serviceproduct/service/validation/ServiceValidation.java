package dev.guilhermevianafreire.ms.serviceproduct.service.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@Scope("singleton")
@RequiredArgsConstructor
public class ServiceValidation {

  private final Validator validator;
  
  public <O> void validate(O dto, Class<?>... groups) {
    Set<ConstraintViolation<O>> violations = validator.validate(dto, groups);
    if (!violations.isEmpty()) {
      StringBuilder sb = new StringBuilder();
      for (ConstraintViolation<O> constraintViolation : violations) {
        if (!sb.isEmpty()) {
          sb.append(", ");
        }
        sb.append("[Parameter: ").append(constraintViolation.getPropertyPath().toString()).append(" | Validation result: ").append(constraintViolation.getMessage()).append("]");
      }

      throw new ConstraintViolationException("Error occurred: " + sb.toString(), violations);
    }
  }
  
  public <O> void validate(O dto) {
    Set<ConstraintViolation<O>> violations = validator.validate(dto);
    if (!violations.isEmpty()) {
      StringBuilder sb = new StringBuilder();
      for (ConstraintViolation<O> constraintViolation : violations) {
        if (!sb.isEmpty()) {
          sb.append(", ");
        }
        sb.append("[Parameter: ").append(constraintViolation.getPropertyPath().toString()).append(" | Validation result: ").append(constraintViolation.getMessage()).append("]");
      }

      throw new ConstraintViolationException("Error occurred: " + sb.toString(), violations);
    }
  }
  
}
