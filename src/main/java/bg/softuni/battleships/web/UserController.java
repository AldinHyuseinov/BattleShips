package bg.softuni.battleships.web;

import bg.softuni.battleships.models.dto.LoginUserDTO;
import bg.softuni.battleships.models.dto.RegisterUserDTO;
import bg.softuni.battleships.models.entities.User;
import bg.softuni.battleships.services.UserService;
import bg.softuni.battleships.utils.user.CurrentUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/users")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class UserController {
    private final UserService userService;

    private CurrentUser currentUser;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @ModelAttribute("userModel")
    public RegisterUserDTO initUserRegister() {
        return new RegisterUserDTO();
    }

    @PostMapping("/register")
    public String register(@Valid RegisterUserDTO userModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel",
                    bindingResult);
            return "redirect:/users/register";
        }
        userService.registerUser(userModel);

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @ModelAttribute("userLoginModel")
    public LoginUserDTO initUserLogin() {
        return new LoginUserDTO();
    }

    @PostMapping("/login")
    public String login(@Valid LoginUserDTO userLoginModel, BindingResult bindingResult,
                        RedirectAttributes redirectAttributes, HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginModel", userLoginModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginModel",
                    bindingResult);
            return "redirect:/users/login";
        }

        Optional<User> user = userService.loginUser(userLoginModel);

        if (user.isPresent()) {
            currentUser.setUser(user.get());
            currentUser.setLoggedIn(true);
            httpSession.setAttribute("userSession", currentUser);

            return "redirect:/home";
        }

        redirectAttributes.addFlashAttribute("userNotValid", "Enter valid username and password");

        return "redirect:/users/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        currentUser.setUser(null);
        currentUser.setLoggedIn(false);
        httpSession.invalidate();

        return "redirect:/";
    }
}
