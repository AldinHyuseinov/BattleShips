package bg.softuni.battleships.models.dto;

import bg.softuni.battleships.models.entities.Ship;
import bg.softuni.battleships.models.enums.CategoryName;
import bg.softuni.battleships.utils.validation.NotFutureDate;
import bg.softuni.battleships.utils.validation.UniqueName;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class AddShipDTO {
    @Size(min = 2, max = 10, message = "The name must be between 2 and 10 characters.")
    @UniqueName(entity = Ship.class)
    private String name;

    @NotNull(message = "The power must not be empty.")
    @Min(value = 1, message = "The power must be positive.")
    private Long power;

    @NotNull(message = "The health must not be empty.")
    @Min(value = 1, message = "The health must be positive.")
    private Long health;

    @NotFutureDate
    private LocalDate created;

    @NotNull(message = "You must select the category.")
    private CategoryName category;
}
