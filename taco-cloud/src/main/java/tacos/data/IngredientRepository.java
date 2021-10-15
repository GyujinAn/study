package tacos.data;

import tacos.Ingredient;

/**
 * @author agj017@gmail.com
 * @since 2021/10/14
 */
public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Ingredient findById(String id);
    Ingredient save(Ingredient ingredient);
}
