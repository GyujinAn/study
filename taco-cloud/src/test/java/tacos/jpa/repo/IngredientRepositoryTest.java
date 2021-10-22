package tacos.jpa.repo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tacos.Ingredient;
import tacos.data.IngredientRepository;

/**
 * @author agj017@gmail.com
 * @since 2021/10/21
 */
class IngredientRepositoryTest {

    @Autowired
    IngredientRepository ingredientRepository;

    @Test
    public void testSave() throws Exception{

        ingredientRepository.save(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));

    }

}