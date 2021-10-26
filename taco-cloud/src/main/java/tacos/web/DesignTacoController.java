package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Order;
import tacos.Taco;
import tacos.User;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;
import tacos.data.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author agj017@gmail.com
 * @since 2021/10/07
 *
 */
@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    private TacoRepository tacoRepository;

    private UserRepository userRepository;

    @Autowired
    public DesignTacoController(
            IngredientRepository ingredientRepository,
            TacoRepository tacoRepository,
            UserRepository userRepository){
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
        this.userRepository = userRepository;
    }

    //HttpServletRequest는 테스트용으로 만들어 봄 비즈니스 로직상 필요한게 아님
    @GetMapping
    public String showDesignForm(Model model, HttpServletRequest request, Principal principal){

        HttpSession session = request.getSession();

//        List<Ingredient> ingredients = Arrays.asList(
//                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
//                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
//                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
//                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
//                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
//                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
//                new Ingredient("CHED", "Cheddar", Type.CHEESE),
//                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
//                new Ingredient("SLSA", "Salsa", Type.SAUCE),
//                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
//        );

//        int [] arr = new int[5];
//        Arrays.setAll(arr, (i) -> (int)(Math.random()*5)+1);

        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(i -> ingredients.add(i));


        Type[] types = Ingredient.Type.values();

        for(Type type : types){
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }


//        model.addAttribute("taco", new Taco());

        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        model.addAttribute("user", user);


        log.info("model in showDesignForm: "+model.toString());
        log.info("session in showDesignForm: "+session.toString());
        log.info("order in session: "+session.getAttribute("order"));
        log.info("taco in session: "+session.getAttribute("taco"));


        return "design";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {

        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @ModelAttribute(name ="order")
    public Order order(){
        log.info("DesignTacoController.order() is called");
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        log.info("DesignTacoController.taco() is called");
        return new Taco();
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order, HttpSession session, Model model){
        log.info("started processDesign");
        if (errors.hasErrors()){
            log.info(errors.toString());
            return "design";
        }

        log.info("session in processDesign " + session.getAttribute("order"));
        log.info("session in processDesign " + session.getAttribute("taco"));
        log.info("model in processDesign " + model.toString());

        log.info("Processing design: " + design);
        Taco saved = tacoRepository.save(design);
        order.addDesign(saved);

        return "redirect:/orders/current";
    }

}
