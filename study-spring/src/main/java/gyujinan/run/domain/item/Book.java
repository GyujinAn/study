package gyujinan.run.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author agj017@gmail.com
 * @since 2021/09/13
 */
@Entity
@DiscriminatorValue("B")
@Getter
@Setter
public class Book extends Item{

    private String author;
    private String isbn;

}
