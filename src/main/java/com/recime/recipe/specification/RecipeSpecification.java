package com.recime.recipe.specification;

import com.recime.recipe.entity.Recipe;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class RecipeSpecification {
    public static Specification<Recipe> hasVegetarian(Boolean vegetarian) {
        return (root, query, cb) -> vegetarian == null
                ? null
                : cb.equal(root.get("vegetarian"), vegetarian);
    }

    public static Specification<Recipe> hasServings(Integer servings) {
        return (root, query, cb) -> servings == null
                ? null
                : cb.equal(root.get("servings"), servings);
    }

    public static Specification<Recipe> includesIngredient(String ingredient) {
        return (root, query, cb) -> {
            if (ingredient == null || ingredient.isBlank()) {
                return null;
            }

            Join<Object, Object> join = root.join("ingredients");
            return cb.like(cb.lower(join.as(String.class)), "%" + ingredient.toLowerCase() + "%");
        };
    }

    public static Specification<Recipe> excludesIngredient(String ingredient) {
        return (root, query, cb) -> {
            if (ingredient == null || ingredient.isBlank()) {
                return null;
            }

            Join<Object, Object> join = root.join("ingredients");
            return cb.notLike(cb.lower(join.as(String.class)), "%" + ingredient.toLowerCase() + "%");
        };
    }

    public static Specification<Recipe> instructionContains(String keyword) {
        return (root, query, cb) -> {
            if (keyword == null || keyword.isBlank()) {
                return null;
            }

            return cb.like(
                    cb.lower(root.get("instructions")),
                    "%" + keyword.toLowerCase() + "%"
            );
        };
    }
}
