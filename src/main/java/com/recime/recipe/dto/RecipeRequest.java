package com.recime.recipe.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class RecipeRequest {
    @NotBlank
    private String title;

    private String description;

    @NotNull
    private Integer servings;

    @NotNull
    private Boolean vegetarian;

    private List<String> ingredients;

    @NotBlank
    private String instructions;
}
