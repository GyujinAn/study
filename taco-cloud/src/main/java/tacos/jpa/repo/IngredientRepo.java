package tacos.jpa.repo;

import org.springframework.data.repository.CrudRepository;
import tacos.jpa.entity.IngredientEntity;

/**
 * @author agj017@gmail.com
 * @since 2021/10/19
 */
public interface IngredientRepo extends CrudRepository<IngredientEntity, String> {

}
