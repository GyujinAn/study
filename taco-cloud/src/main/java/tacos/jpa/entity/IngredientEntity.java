package tacos.jpa.entity;

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
@NoArgsConstructor(access=AccessLevel.PUBLIC, force=true)
@Entity
public class IngredientEntity {

    @Id
    private final String id;
    private final String name;
    private final Type type;

    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
