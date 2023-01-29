package bg.softuni.battleships.models.dto;

import bg.softuni.battleships.models.entities.User;
import bg.softuni.battleships.utils.validation.FieldMatch;
import bg.softuni.battleships.utils.validation.UniqueEmail;
import bg.softuni.battleships.utils.validation.UniqueName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@FieldMatch(first = "password", second = "confirmPassword", message = "Passwords must match.")
public class RegisterUserDTO {
    @Size(min = 3, max = 10, message = "Username length must be between 3 and 10 characters.")
    @UniqueName(entity = User.class, message = "Username already exists.")
    private String username;

    @Size(min = 5, max = 20, message = "Full name length must be between 5 and 20 characters.")
    private String fullName;

    @Email(regexp = ".+@.+", message = "Enter valid email address.")
    @UniqueEmail
    private String email;

    @Size(min = 3, message = "Password length must be more than 3 characters.")
    private String password;

    @Size(min = 3, message = "Password length must be more than 3 characters.")
    private String confirmPassword;
}
