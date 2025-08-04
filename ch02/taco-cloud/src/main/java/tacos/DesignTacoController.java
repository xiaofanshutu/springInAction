package tacos;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {
    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla(面粉玉米饼)", Ingredient.Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla(玉米饼)", Ingredient.Type.WRAP),
                new Ingredient("GRBF", "Ground Beef(搅碎的牛肉)", Ingredient.Type.PROTEIN),
                new Ingredient("CARN", "Carnitas(卡尼塔斯小肉)", Ingredient.Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes(西红柿丁)", Ingredient.Type.VEGGIES),
                new Ingredient("LETC", "Lettuce(生菜)", Ingredient.Type.VEGGIES),
                new Ingredient("CHED", "Cheddar(切达干酪)", Ingredient.Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack(蒙特雷杰克)", Ingredient.Type.CHEESE),
                new Ingredient("SLSA", "Salsa(调味汁)", Ingredient.Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream(酸奶油)", Ingredient.Type.SAUCE)
        );

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder tacoOrder() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
//        log.info("Displaying design form");
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
        if(errors.hasErrors()){
            return "design";
        }

        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);
        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }
}
