package tacos.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import tacos.Ingredient;

import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
    //使用CrudRepository的时候可以省略下面的代码

    /*
    Iterable<Ingredient> findAll();
    Optional<Ingredient> findById(String id);
    Ingredient save(Ingredient ingredient);

     */
}
