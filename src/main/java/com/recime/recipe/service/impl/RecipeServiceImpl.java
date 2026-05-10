package com.recime.recipe.service.impl;

import com.recime.recipe.dto.RecipeRequest;
import com.recime.recipe.dto.RecipeResponse;
import com.recime.recipe.entity.Recipe;
import com.recime.recipe.exception.ResourceNotFoundException;
import com.recime.recipe.repository.RecipeRepository;
import com.recime.recipe.service.RecipeService;
import com.recime.recipe.specification.RecipeSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Override
    public RecipeResponse createRecipe(RecipeRequest request) {

        Recipe recipe = Recipe.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .servings(request.getServings())
                .vegetarian(request.getVegetarian())
                .ingredients(request.getIngredients())
                .instructions(request.getInstructions())
                .build();

        Recipe savedRecipe = recipeRepository.save(recipe);

        return mapToResponse(savedRecipe);
    }

    @Override
    @Transactional(readOnly = true)
    public RecipeResponse getRecipe(Long id) {

        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Recipe not found with id: " + id)
                );

        return mapToResponse(recipe);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecipeResponse> getAllRecipes() {

        return recipeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public RecipeResponse updateRecipe(Long id, RecipeRequest request) {

        Recipe existingRecipe = recipeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Recipe not found with id: " + id)
                );

        existingRecipe.setTitle(request.getTitle());
        existingRecipe.setDescription(request.getDescription());
        existingRecipe.setServings(request.getServings());
        existingRecipe.setVegetarian(request.getVegetarian());
        existingRecipe.setIngredients(request.getIngredients());
        existingRecipe.setInstructions(request.getInstructions());

        Recipe updatedRecipe = recipeRepository.save(existingRecipe);

        return mapToResponse(updatedRecipe);
    }

    @Override
    public void deleteRecipe(Long id) {

        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Recipe not found with id: " + id)
                );

        recipeRepository.delete(recipe);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecipeResponse> searchRecipes(
            Boolean vegetarian,
            Integer servings,
            String includeIngredient,
            String excludeIngredient,
            String instructionKeyword
    ) {

        Specification<Recipe> specification = Specification
                .where(RecipeSpecification.hasVegetarian(vegetarian))
                .and(RecipeSpecification.hasServings(servings))
                .and(RecipeSpecification.includesIngredient(includeIngredient))
                .and(RecipeSpecification.excludesIngredient(excludeIngredient))
                .and(RecipeSpecification.instructionContains(instructionKeyword));

        return recipeRepository.findAll(specification)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private RecipeResponse mapToResponse(Recipe recipe) {

        return RecipeResponse.builder()
                .id(recipe.getId())
                .title(recipe.getTitle())
                .description(recipe.getDescription())
                .servings(recipe.getServings())
                .vegetarian(recipe.getVegetarian())
                .ingredients(recipe.getIngredients())
                .instructions(recipe.getInstructions())
                .build();
    }
}
