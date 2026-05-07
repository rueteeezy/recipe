package com.recime.recipe.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RecipeResponse {

    private Long id;
    private String title;
    private String description;
    private Integer servings;
    private Boolean vegetarian;
    private List<String> ingredients;
    private String instructions;

}
