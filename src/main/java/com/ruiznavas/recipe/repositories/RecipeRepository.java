package com.ruiznavas.recipe.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ruiznavas.recipe.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{

}
