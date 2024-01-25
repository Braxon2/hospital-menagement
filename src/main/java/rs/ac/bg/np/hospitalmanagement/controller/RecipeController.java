package rs.ac.bg.np.hospitalmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.np.hospitalmanagement.domain.Recipe;
import rs.ac.bg.np.hospitalmanagement.repository.RecipeRepository;
import rs.ac.bg.np.hospitalmanagement.service.RecipeService;

import java.util.List;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping("/repfrom/{repid}/medicine/{mid}")
    public Recipe createRecipe(@PathVariable long repid, @PathVariable long mid, @RequestBody Recipe recipe) throws Exception {
        return recipeService.createRecipe(repid,mid,recipe);
    }

    @GetMapping
    public List<Recipe> getAllRecipes(){
        return recipeService.getAll();
    }

}
