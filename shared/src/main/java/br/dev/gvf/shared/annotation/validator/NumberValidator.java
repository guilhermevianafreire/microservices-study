package br.dev.gvf.shared.annotation.validator;

import br.dev.gvf.shared.validation.NumberPattern;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class NumberValidator implements ConstraintValidator<Number, String> {

    @Override
    public boolean isValid(
            String value,
            ConstraintValidatorContext context
    ) {
        if (StringUtils.isBlank(value))
            return false;
        return Pattern.matches(
                NumberPattern.REGEX_NUMBERS_GENERAL.getPattern(),
                value
        );
    }
}
