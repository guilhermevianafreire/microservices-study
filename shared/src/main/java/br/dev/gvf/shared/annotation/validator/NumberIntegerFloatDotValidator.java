package br.dev.gvf.shared.annotation.validator;

import br.dev.gvf.shared.validation.NumberPattern;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class NumberIntegerFloatDotValidator implements ConstraintValidator<NumberIntegerFloatDot, String> {
    @Override
    public boolean isValid(
            String value,
            ConstraintValidatorContext context
    ) {
        return Pattern.matches(
                NumberPattern.REGEX_NUMBERS_GENERAL_DOT.getPattern(),
                value
        );
    }
}
