package gyujinan.run.domain;

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
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    //하나의 회원이 여러개의 주문을 하므로 일대다 관계가 된다.
    //외래키가 가지고 있지 않은 필드에 mappedBy를 통해서 연관관계의 주인이 아님을 선언해야 된다.
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}
