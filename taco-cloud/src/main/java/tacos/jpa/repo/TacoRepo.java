package tacos.jpa.repo;

import org.springframework.data.repository.CrudRepository;
import tacos.jpa.entity.IngredientEntity;
import tacos.jpa.entity.TacoEntity;

/**
 * @author agj017@gmail.com
 * @since 2021/10/19
 */
public interface TacoRepo extends CrudRepository<TacoEntity, Long> {
}
