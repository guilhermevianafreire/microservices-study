package br.dev.gvf.shared.annotation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class OrderByValidator implements ConstraintValidator<OrderBy, String> {

    public static final String REGEX = "([a-z A-Z]+):(asc|desc)(,?)";

    @Override
    public boolean isValid(
            String value,
            ConstraintValidatorContext context
    ) {
        if (StringUtils.isBlank(value))
            return true;
        return Pattern.matches(REGEX, value);
    }
}
