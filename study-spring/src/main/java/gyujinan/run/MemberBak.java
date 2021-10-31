package gyujinan.run;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author agj017@gmail.com
 * @since 2021/09/07
 */

@Entity
@Getter
@Setter
public class MemberBak {


    @Id @GeneratedValue
    private Long id;
    private String username;
}
