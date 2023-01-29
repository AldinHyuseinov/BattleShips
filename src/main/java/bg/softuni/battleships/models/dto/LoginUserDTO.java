package bg.softuni.battleships.models.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LoginUserDTO {
    @Size(min = 3, max = 10, message = "Username length must be between 3 and 10 characters.")
    private String username;

    @Size(min = 3, message = "Password length must be more than 3 characters.")
    private String password;
}
