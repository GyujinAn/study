package tacos;

import lombok.Data;

import java.util.List;

/**
 * @author agj017@gmail.com
 * @since 2021/10/07
 */
@Data
public class Taco {
    private String name;
    private List<String> ingredients;
}
