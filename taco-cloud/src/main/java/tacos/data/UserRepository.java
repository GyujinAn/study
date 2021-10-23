package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.User;

/**
 * @author agj017@gmail.com
 * @since 2021/10/22
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
