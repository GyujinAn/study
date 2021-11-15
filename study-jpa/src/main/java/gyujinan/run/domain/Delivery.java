package gyujinan.run.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author agj017@gmail.com
 * @since 2021/09/13
 */

@Entity
@Getter
@Setter
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;


    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    //Enumerated의 타입은 꼭 String으로 하자!! ORDINAL할 경우 숫자로 표현됨으로 정확한 식별이 불가
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //READY, XXX, COMP

}
