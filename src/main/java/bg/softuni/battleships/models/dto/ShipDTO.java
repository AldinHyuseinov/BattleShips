package bg.softuni.battleships.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ShipDTO {
    private String name;

    private Long health;

    private Long power;
}
