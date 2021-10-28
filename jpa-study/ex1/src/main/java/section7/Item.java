package section7;

import javax.persistence.*;

/**
 * @author gj.an@okestro.com
 * @since 2021/07/09
 */

@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) //사용하지말것
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Inheritance(strategy = InheritanceType.JOINED) //기본적으로 사용해야됨
@DiscriminatorColumn(name = "DIS_TYPE")
public abstract class Item extends BaseEntity{

    @Id @GeneratedValue
    private Long id;

    private String name;

    private int price;



}

