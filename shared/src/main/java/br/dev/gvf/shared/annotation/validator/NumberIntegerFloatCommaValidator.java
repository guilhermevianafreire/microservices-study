package br.dev.gvf.shared.annotation.validator;

import br.dev.gvf.shared.validation.NumberPattern;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class NumberIntegerFloatCommaValidator implements ConstraintValidator<NumberIntegerFloatComma, String> {
    @Override
    public boolean isValid(
            String value,
            ConstraintValidatorContext context
    ) {
        return Pattern.matches(
                NumberPattern.REGEX_NUMBERS_GENERAL_COMMA.getPattern(),
                value
        );
    }
}