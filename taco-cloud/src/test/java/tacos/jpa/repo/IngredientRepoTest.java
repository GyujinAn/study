package tacos.jpa.repo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tacos.jpa.entity.IngredientEntity;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author agj017@gmail.com
 * @since 2021/10/21
 */
class IngredientRepoTest {

    @Autowired
    IngredientRepo ingredientRepo;

    @Test
    public void testSave() throws Exception{

        ingredientRepo.save(new IngredientEntity("FLTO", "Flour Tortilla", IngredientEntity.Type.WRAP));

    }

}