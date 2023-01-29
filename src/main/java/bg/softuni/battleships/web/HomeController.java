package bg.softuni.battleships.web;

import bg.softuni.battleships.services.ShipService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor(onConstructor_ = @Autowired)
public class HomeController {
    private final ShipService shipService;

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("currentUserShips", shipService.currentUserShips());
        model.addAttribute("otherUserShips", shipService.otherUserShips());
        model.addAttribute("allShips", shipService.allShips());

        return "home";
    }

    @PostMapping("/home")
    public String homePage(@RequestParam("currentUserShipName") String currentUserShipName,
                           @RequestParam("otherUserShipName") String otherUserShipName) {
        shipService.fight(currentUserShipName, otherUserShipName);

        return "redirect:/home";
    }
}
