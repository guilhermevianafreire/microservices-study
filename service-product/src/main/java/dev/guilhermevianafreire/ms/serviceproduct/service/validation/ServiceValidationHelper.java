package dev.guilhermevianafreire.ms.serviceproduct.service.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dev.guilhermevianafreire.ms.serviceproduct.stream.StreamUtil;
import dev.guilhermevianafreire.ms.serviceproduct.util.LocaleUtil;
import lombok.RequiredArgsConstructor;

@Component
@Scope("singleton")
@RequiredArgsConstructor
public class ServiceValidationHelper {

  private final Validator validator;
  private final LocaleUtil localeUtil; 
  private final MessageSource errorMessagesSource;
  
  public <O> void validate(O dto, Class<?>... groups) {
    throwConstraintVioationException(validator.validate(dto, groups));
  }

  public <O> void validate(O dto) {
    throwConstraintVioationException(validator.validate(dto));
  }
  
  private <O> void throwConstraintVioationException(Set<ConstraintViolation<O>> violations) {
    if (!violations.isEmpty()) {
      StringBuilder messages = new StringBuilder(errorMessagesSource.getMessage("service.constraint.violation.title", null, localeUtil.getCurrentLocale()));
      violations.stream().forEach(StreamUtil.withCounter((i, constraintViolation) -> {
        if (i == 0)
          messages.append(errorMessagesSource.getMessage("service.constraint.violation.message", new Object[] {constraintViolation.getRootBeanClass().getName(), constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage()}, localeUtil.getCurrentLocale()));
        else
          messages.append(" | ").append(errorMessagesSource.getMessage("service.constraint.violation.message", new Object[] {constraintViolation.getRootBeanClass().getName(), constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage()}, localeUtil.getCurrentLocale()));
      }));
      throw new ConstraintViolationException(messages.toString(), violations);
    }
  }
  
}
