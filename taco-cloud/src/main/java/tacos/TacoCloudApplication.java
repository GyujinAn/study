package tacos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tacos.data.IngredientRepository;
import tacos.jpa.entity.IngredientEntity;
import tacos.jpa.entity.IngredientEntity.Type;
import tacos.jpa.repo.IngredientRepo;

@Slf4j
@SpringBootApplication
public class TacoCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);

    }

//    @Bean
//    public CommandLineRunner dataLoader(IngredientRepo repo) {
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... args) throws Exception {
//
//                repo.save(new IngredientEntity("FLTO", "Flour Tortilla", Type.WRAP));
//                repo.save(new IngredientEntity("COTO", "Corn Tortilla", Type.WRAP));
//                repo.save(new IngredientEntity("GRBF", "Ground Beef", Type.PROTEIN));
//                repo.save(new IngredientEntity("CARN", "Carnitas", Type.PROTEIN));
//                repo.save(new IngredientEntity("TMTO", "Diced Tomatoes", Type.VEGGIES));
//                repo.save(new IngredientEntity("LETC", "Lettuce", Type.VEGGIES));
//                repo.save(new IngredientEntity("CHED", "Cheddar", Type.CHEESE));
//                repo.save(new IngredientEntity("JACK", "Monterrey Jack", Type.CHEESE));
//                repo.save(new IngredientEntity("SLSA", "Salsa", Type.SAUCE));
//                repo.save(new IngredientEntity("SRCR", "Sour Cream", Type.SAUCE));
//            }
//        };
//    }

}
