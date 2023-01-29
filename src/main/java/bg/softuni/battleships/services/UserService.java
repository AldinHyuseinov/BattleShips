package bg.softuni.battleships.services;

import bg.softuni.battleships.models.dto.LoginUserDTO;
import bg.softuni.battleships.models.dto.RegisterUserDTO;
import bg.softuni.battleships.models.entities.User;
import bg.softuni.battleships.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class UserService {
    private final UserRepository userRepository;

    private final ModelMapper mapper;

    public void registerUser(RegisterUserDTO registerUserDTO) {
        userRepository.saveAndFlush(mapper.map(registerUserDTO, User.class));
    }

    public Optional<User> loginUser(LoginUserDTO loginUserDTO) {
        return userRepository.findByUsernameAndPassword(loginUserDTO.getUsername(), loginUserDTO.getPassword());
    }
}
