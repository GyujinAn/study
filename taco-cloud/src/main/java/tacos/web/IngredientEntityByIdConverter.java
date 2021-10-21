package tacos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tacos.jpa.entity.IngredientEntity;
import tacos.jpa.repo.IngredientRepo;

import java.util.Optional;

/**
 * @author agj017@gmail.com
 * @since 2021/10/19
 */

@Component
public class IngredientEntityByIdConverter implements Converter<String, IngredientEntity> {
    private IngredientRepo ingredientRepository;

    @Autowired
    public IngredientEntityByIdConverter(IngredientRepo ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public IngredientEntity convert(String id) {
        Optional<IngredientEntity> optionalIngredientEntity = ingredientRepository.findById(id);


        return optionalIngredientEntity.isPresent() ? optionalIngredientEntity.get() : null;
    }
}