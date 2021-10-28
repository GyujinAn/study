package jpabopok.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gj.an@okestro.com
 * @since 2021/07/02
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
public abstract class Item extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
}
