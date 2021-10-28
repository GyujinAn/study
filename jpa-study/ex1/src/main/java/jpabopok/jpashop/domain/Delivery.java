package jpabopok.jpashop.domain;

import javax.persistence.*;

/**
 * @author gj.an@okestro.com
 * @since 2021/07/08
 */
@Entity
public class Delivery  extends BaseEntity{

    @Id @GeneratedValue
    private Long id;

    @Embedded
    private Address address;

    private DeliveryStatus status;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "delivery")
    private Order order;
}
