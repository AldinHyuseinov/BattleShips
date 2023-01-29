package bg.softuni.battleships.utils.validation;

import bg.softuni.battleships.models.entities.BaseEntity;
import bg.softuni.battleships.models.entities.Ship;
import bg.softuni.battleships.models.entities.User;
import bg.softuni.battleships.repositories.ShipRepository;
import bg.softuni.battleships.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class UniqueNameValidator implements ConstraintValidator<UniqueName, String> {
    private Class<? extends BaseEntity> entity;

    private final ApplicationContext applicationContext;

    @Autowired
    public UniqueNameValidator(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void initialize(UniqueName constraintAnnotation) {
        entity = constraintAnnotation.entity();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean isUnique = false;

        if (entity == User.class) {
            UserRepository userRepository = applicationContext.getBean(UserRepository.class);

            isUnique = userRepository.findByUsername(value).isEmpty();
        } else if (entity == Ship.class) {
            ShipRepository shipRepository = applicationContext.getBean(ShipRepository.class);

            isUnique = shipRepository.findByName(value).isEmpty();
        }
        return isUnique;
    }
}
