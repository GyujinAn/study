package section7;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author gj.an@okestro.com
 * @since 2021/07/09
 */


@Entity
@DiscriminatorValue("B")
public class Book extends Item{
    private String author;
    private String isbn;


}
