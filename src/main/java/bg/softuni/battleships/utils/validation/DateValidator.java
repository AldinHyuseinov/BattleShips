package bg.softuni.battleships.utils.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class DateValidator implements ConstraintValidator<NotFutureDate, LocalDate> {

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {

        if (value != null) {
            return value.isBefore(LocalDate.now().plusDays(1));
        }
        context.buildConstraintViolationWithTemplate("Created date cannot be empty.")
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
