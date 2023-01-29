package bg.softuni.battleships.utils.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = DateValidator.class)
public @interface NotFutureDate {
    String message() default "Created date cannot be in the future.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
