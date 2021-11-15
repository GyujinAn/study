package gyujinan.run.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author agj017@gmail.com
 * @since 2021/09/13
 */

@Entity
//Order는 sql예약어라서 테이블 이름으로 사용하지 않고 관습상 orders라고 사용하는 경우가 많음
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue
    //DBA는 id에 name 속성을 사용하여 구체적으로 order_id와 같이 사용하는 것을 선호함
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    //@JoinColumn 기능에 대해서 잘 생각이 안남
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    //일대일 관계은 경우에는 Order에 접근하는 경우가 많을거라 예상되므로 연관관계의 주인을 Order로 한다.
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    //Data를 사용하면 어노테이션을 붙여야되지만 LocalDataTime은 하이버네이트가 알아서 지원해주니 LocalDataTime를 사용할 것
    //private Data date;

    private LocalDateTime orderData;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;


}
