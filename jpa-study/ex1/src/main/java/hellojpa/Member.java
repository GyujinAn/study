package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author gj.an@okestro.com
 * @since 2021/06/23
 */
//@Entity jpa가 인식 할 수 있도록 설정
@Entity
public class Member {

    @Id
    private Long id;
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
