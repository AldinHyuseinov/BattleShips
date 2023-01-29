package bg.softuni.battleships.models.entities;

import bg.softuni.battleships.models.enums.CategoryName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@Getter
@Setter
public class Category extends BaseEntity {
    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryName name;

    @Column(columnDefinition = "TEXT")
    private String description;
}
