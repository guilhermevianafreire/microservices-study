package dev.guilhermevianafreire.ms.serviceproduct.service.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ServiceValidationHelper {

    private final Validator validator;

    public <O> void validate(O dto, Class<?>... groups) {
        throwConstraintViolationException(validator.validate(dto, groups));
    }

    public <O> void validate(O dto) {
        throwConstraintViolationException(validator.validate(dto));
    }

    private <O> void throwConstraintViolationException(Set<ConstraintViolation<O>> violations) {
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

}
