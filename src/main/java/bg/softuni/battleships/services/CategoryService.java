package bg.softuni.battleships.services;

import bg.softuni.battleships.models.entities.Category;
import bg.softuni.battleships.models.enums.CategoryName;
import bg.softuni.battleships.repositories.CategoryRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @PostConstruct
    public void importCategories() {

        if (categoryRepository.count() <= 0) {
            Category battleCategory = new Category();
            battleCategory.setName(CategoryName.BATTLE);
            battleCategory.setDescription("A ship for battle.");

            Category cargoCategory = new Category();
            cargoCategory.setName(CategoryName.CARGO);
            cargoCategory.setDescription("A ship for trading.");

            Category patrolCategory = new Category();
            patrolCategory.setName(CategoryName.PATROL);
            patrolCategory.setDescription("A ship for patrolling around");

            categoryRepository.saveAll(List.of(battleCategory, cargoCategory, patrolCategory));
        }
    }
}
