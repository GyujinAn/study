package gyujinan.run.domain.item;

import gyujinan.run.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author agj017@gmail.com
 * @since 2021/09/13
 */

@Entity
//Inheritance 전략 JOINED로 해서 결과 출력해보기
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
//Setter는 실무 때 꼭 필요한 경우만 만들어서 사용한다.
@Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();



}
