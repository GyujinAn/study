package jpabopok.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gj.an@okestro.com
 * @since 2021/07/02
 */

@Entity
public class Member  extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String username;

    private Integer age;

    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    //@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Team team;

    @OneToMany(mappedBy = "mamber", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    @Lob
    private String description;
}
