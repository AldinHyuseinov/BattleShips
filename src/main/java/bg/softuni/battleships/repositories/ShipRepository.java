package bg.softuni.battleships.repositories;

import bg.softuni.battleships.models.entities.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {
    Optional<Ship> findByName(String name);

    List<Ship> findAllByUserId(Long userId);

    @Query("SELECT s FROM Ship AS s JOIN s.user AS u WHERE u.username<>:username")
    List<Ship> findOtherUserShips(String username);

    @Query("SELECT s FROM Ship AS s ORDER BY s.name, s.health, s.power")
    List<Ship> findAllShips();
}
