package tacos.data;

import tacos.Order;

/**
 * @author agj017@gmail.com
 * @since 2021/10/14
 */
public interface OrderRepository {
    Order save(Order order);
}
