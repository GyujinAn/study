package section7;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author gj.an@okestro.com
 * @since 2021/07/09
 */


@Entity
@DiscriminatorValue("M")
public class Movie extends Item{
    private String director;
    private String actor;
}
