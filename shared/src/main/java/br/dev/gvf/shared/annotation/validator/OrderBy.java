package br.dev.gvf.shared.annotation.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import static java.lang.annotation.ElementType.*;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target( { FIELD, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = OrderByValidator.class)
public @interface OrderBy {
    String message() default "Invalid orderBy value. List of property names and directions concatenated by ':' and ',' . Ex: name:asc,email:desc";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
