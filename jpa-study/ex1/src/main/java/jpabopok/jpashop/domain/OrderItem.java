package jpabopok.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author gj.an@okestro.com
 * @since 2021/07/02
 */

@Entity
public class OrderItem  extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    private LocalDateTime orderDate;

    @Enumerated
    private OrderStatus status;

    public void setOrder(Order order) {
    }
}
