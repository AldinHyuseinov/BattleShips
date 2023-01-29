package bg.softuni.battleships.web;

import bg.softuni.battleships.models.dto.AddShipDTO;
import bg.softuni.battleships.services.ShipService;
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

@Controller
@RequestMapping("/ships")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class ShipController {
    private final ShipService shipService;

    @GetMapping("/add")
    public String addShip() {
        return "ship-add";
    }

    @ModelAttribute("shipModel")
    public AddShipDTO initAddShip() {
        return new AddShipDTO();
    }

    @PostMapping("/add")
    public String addShip(@Valid AddShipDTO shipModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("shipModel", shipModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.shipModel",
                    bindingResult);
            return "redirect:/ships/add";
        }
        shipService.addShip(shipModel);

        return "redirect:/home";
    }
}
