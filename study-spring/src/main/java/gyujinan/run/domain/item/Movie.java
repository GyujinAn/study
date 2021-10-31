package gyujinan.run.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author agj017@gmail.com
 * @since 2021/09/13
 */
@Entity
@DiscriminatorValue("M")
@Getter
@Setter
public class Movie extends Item {

    private String director;
    private String actor;

}
