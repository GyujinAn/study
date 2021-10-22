package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.Order;


/**
 * @author agj017@gmail.com
 * @since 2021/10/19
 */
public interface OrderRepository extends CrudRepository<Order, Long> {
}
