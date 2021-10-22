package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.Ingredient;

/**
 * @author agj017@gmail.com
 * @since 2021/10/19
 */
public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
