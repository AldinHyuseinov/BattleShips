package bg.softuni.battleships.utils.user;

import bg.softuni.battleships.models.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@Getter
@Setter
public class CurrentUser {
    private User user;

    private boolean loggedIn;
}
