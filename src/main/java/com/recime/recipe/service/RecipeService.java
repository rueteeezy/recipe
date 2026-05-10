package com.recime.recipe.service;

import com.recime.recipe.dto.RecipeRequest;
import com.recime.recipe.dto.RecipeResponse;

import java.util.List;

public interface RecipeService {

    RecipeResponse createRecipe(RecipeRequest request);

    RecipeResponse getRecipe(Long id);

    List<RecipeResponse> getAllRecipes();

    RecipeResponse updateRecipe(Long id, RecipeRequest request);

    void deleteRecipe(Long id);

    List<RecipeResponse> searchRecipes(
            Boolean vegetarian,
            Integer servings,
            String includeIngredient,
            String excludeIngredient,
            String instructionKeyword
    );
}
