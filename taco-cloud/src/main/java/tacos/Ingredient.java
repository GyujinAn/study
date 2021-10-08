package tacos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author agj017@gmail.com
 * @since 2021/10/07
 */
@Data
@RequiredArgsConstructor
public class Ingredient {
    private final String id;
    private final String name;
    private final Type type;

    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

}
