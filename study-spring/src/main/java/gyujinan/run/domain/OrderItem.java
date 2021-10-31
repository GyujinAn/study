package gyujinan.run.domain;

import gyujinan.run.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author agj017@gmail.com
 * @since 2021/09/13
 */

@Getter
@Setter
@Entity
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; //주문 가격

    private int count; //주문 수량

}
