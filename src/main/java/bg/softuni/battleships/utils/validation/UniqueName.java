package bg.softuni.battleships.utils.validation;

import bg.softuni.battleships.models.entities.BaseEntity;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueNameValidator.class)
public @interface UniqueName {
    String message() default "Element with the given name already exists!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends BaseEntity> entity();
}
