package bg.softuni.battleships.services;

import bg.softuni.battleships.models.dto.AddShipDTO;
import bg.softuni.battleships.models.dto.ShipDTO;
import bg.softuni.battleships.models.entities.Ship;
import bg.softuni.battleships.repositories.CategoryRepository;
import bg.softuni.battleships.repositories.ShipRepository;
import bg.softuni.battleships.repositories.UserRepository;
import bg.softuni.battleships.utils.user.CurrentUser;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class ShipService {
    private final ShipRepository shipRepository;

    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;

    private CurrentUser currentUser;

    private final ModelMapper mapper;

    public void addShip(AddShipDTO addShipDTO) {
        Ship ship = mapper.map(addShipDTO, Ship.class);
        ship.setCategory(categoryRepository.findByName(addShipDTO.getCategory()));
        ship.setUser(userRepository.findById(currentUser.getUser().getId()).get());

        shipRepository.saveAndFlush(ship);
    }

    public List<ShipDTO> currentUserShips() {
        return shipRepository.findAllByUserId(currentUser.getUser().getId()).stream()
                .map(ship -> mapper.map(ship, ShipDTO.class)).collect(Collectors.toList());
    }

    public List<ShipDTO> otherUserShips() {
        return shipRepository.findOtherUserShips(currentUser.getUser().getUsername()).stream()
                .map(ship -> mapper.map(ship, ShipDTO.class)).collect(Collectors.toList());
    }

    public List<ShipDTO> allShips() {
        return shipRepository.findAllShips().stream().map(ship -> mapper.map(ship, ShipDTO.class))
                .collect(Collectors.toList());
    }

    public void fight(String attackerShipName, String defenderShipName) {
        Ship attackerShip = shipRepository.findByName(attackerShipName).get();
        Ship defenderShip = shipRepository.findByName(defenderShipName).get();

        defenderShip.setHealth(defenderShip.getHealth() - attackerShip.getPower());

        if (defenderShip.getHealth() <= 0) {
            shipRepository.delete(defenderShip);
        } else {
            shipRepository.save(defenderShip);
        }
    }
}
