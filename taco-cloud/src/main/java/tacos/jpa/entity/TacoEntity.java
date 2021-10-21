package tacos.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @author agj017@gmail.com
 * @since 2021/10/07
 */
@Data
@Entity
public class TacoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createdAt;

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    @ManyToMany(targetEntity = IngredientEntity.class)
    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<IngredientEntity> ingredients;

    @PrePersist
    void createdAt(){
        this.createdAt = new Date();
    }
}
