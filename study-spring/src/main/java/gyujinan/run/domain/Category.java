package gyujinan.run.domain;

import gyujinan.run.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author agj017@gmail.com
 * @since 2021/09/14
 */


@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "catagory_id")
    private Long id;

    private String name;


    //실무에서는 다대다는 더 필드를 추가하거나 하는 것이 불가능하다.
    //라고 하는데 잘 이해가 되지 않는다.
    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "catagory_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();


}
