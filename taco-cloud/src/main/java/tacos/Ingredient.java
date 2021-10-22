package tacos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author agj017@gmail.com
 * @since 2021/10/07
 */

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access= AccessLevel.PUBLIC, force=true)
@Entity
public class Ingredient {

    @Id
    private final String id;
    private final String name;
    private final Ingredient.Type type;

    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

}
