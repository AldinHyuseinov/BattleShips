package bg.softuni.battleships.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "user", targetEntity = Ship.class, fetch = FetchType.EAGER)
    private Set<Ship> ships;

    public User() {
        ships = new HashSet<>();
    }
}
