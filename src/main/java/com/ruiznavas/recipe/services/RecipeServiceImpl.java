package com.ruiznavas.recipe.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ruiznavas.recipe.domain.Recipe;
import com.ruiznavas.recipe.repositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
	RecipeRepository recipeRepo;

	public RecipeServiceImpl(RecipeRepository recipeRepo) {
		this.recipeRepo = recipeRepo;
	}

	@Override
	public Set<Recipe> getRecipes() {
		log.debug("Servicio devolviendo recetas");
		
		Set<Recipe> recipeSet = new HashSet<>();
		recipeRepo.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}

}
