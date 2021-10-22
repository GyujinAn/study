package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.Taco;


/**
 * @author agj017@gmail.com
 * @since 2021/10/19
 */
public interface TacoRepository extends CrudRepository<Taco, Long> {
}
