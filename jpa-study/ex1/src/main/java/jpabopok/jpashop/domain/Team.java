package jpabopok.jpashop.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author gj.an@okestro.com
 * @since 2021/07/10
 */
@Entity
public class Team {
    @Id
    @GeneratedValue
    Long id;
}
