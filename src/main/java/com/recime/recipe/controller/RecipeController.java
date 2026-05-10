package com.recime.recipe.controller;

import com.recime.recipe.dto.RecipeRequest;
import com.recime.recipe.dto.RecipeResponse;
import com.recime.recipe.service.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecipeResponse createRecipe(
            @Valid @RequestBody RecipeRequest request
    ) {

        return recipeService.createRecipe(request);
    }

    @GetMapping("/{id}")
    public RecipeResponse getRecipe(@PathVariable Long id) {

        return recipeService.getRecipe(id);
    }

    @GetMapping
    public List<RecipeResponse> getAllRecipes() {

        return recipeService.getAllRecipes();
    }

    @PutMapping("/{id}")
    public RecipeResponse updateRecipe(
            @PathVariable Long id,
            @Valid @RequestBody RecipeRequest request
    ) {

        return recipeService.updateRecipe(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecipe(@PathVariable Long id) {

        recipeService.deleteRecipe(id);
    }

    @GetMapping("/search")
    public List<RecipeResponse> searchRecipes(

            @RequestParam(required = false)
            Boolean vegetarian,

            @RequestParam(required = false)
            Integer servings,

            @RequestParam(required = false)
            String includeIngredient,

            @RequestParam(required = false)
            String excludeIngredient,

            @RequestParam(required = false)
            String instructionKeyword
    ) {

        return recipeService.searchRecipes(
                vegetarian,
                servings,
                includeIngredient,
                excludeIngredient,
                instructionKeyword
        );
    }

}
