package com.ruiznavas.recipe.bootstraps;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.ruiznavas.recipe.domain.Category;
import com.ruiznavas.recipe.domain.Difficulty;
import com.ruiznavas.recipe.domain.Ingredient;
import com.ruiznavas.recipe.domain.Notes;
import com.ruiznavas.recipe.domain.Recipe;
import com.ruiznavas.recipe.domain.UnitOfMeasure;
import com.ruiznavas.recipe.repositories.CategoryRepository;
import com.ruiznavas.recipe.repositories.RecipeRepository;
import com.ruiznavas.recipe.repositories.UnitOfMeasureRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
	RecipeRepository recipeRepository;
	UnitOfMeasureRepository uomRepository;
	CategoryRepository categoryRepository;

	public RecipeBootstrap(RecipeRepository recipeRepository, UnitOfMeasureRepository uomRepository,
			CategoryRepository categoryRepository) {
		this.recipeRepository = recipeRepository;
		this.uomRepository = uomRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		recipeRepository.saveAll(getRecipes());
		log.debug("Cargando Datos Iniciales");
	}
	
	private List<Recipe> getRecipes(){
		List<Recipe> recipes = new ArrayList<>(2);
		
		// Obtenemos las UOMS
		Optional<UnitOfMeasure> eachUomOptional = uomRepository.findByDescription("Each");
		if(!eachUomOptional.isPresent()) {
			throw new RuntimeException("UOM Each no encontrado");
		}
		
		Optional<UnitOfMeasure> tablespoonUomOptional = uomRepository.findByDescription("Tablespoon");
		if(!tablespoonUomOptional.isPresent()) {
			throw new RuntimeException("UOM Tablespoon no encontrado");
		}
		
		Optional<UnitOfMeasure> teaSpoonUomOptional = uomRepository.findByDescription("Teaspoon");
		if(!teaSpoonUomOptional.isPresent()) {
			throw new RuntimeException("UOM TeaSpoon no encontrado");
		}
		
		Optional<UnitOfMeasure> dashUomOptional = uomRepository.findByDescription("Dash");
		if(!dashUomOptional.isPresent()) {
			throw new RuntimeException("UOM Dash no encontrado");
		}
		
		Optional<UnitOfMeasure> pintUomOptional = uomRepository.findByDescription("Pint");
		if(!pintUomOptional.isPresent()) {
			throw new RuntimeException("UOM Pint no encontrado");
		}
		
		Optional<UnitOfMeasure> cupUomOptional = uomRepository.findByDescription("Cup");
		if(!cupUomOptional.isPresent()) {
			throw new RuntimeException("UOM Cup no encontrado");
		}
		
		// Obtenemos los opcionales
		UnitOfMeasure eachUom = eachUomOptional.get();
		UnitOfMeasure tablespoonUom = tablespoonUomOptional.get();
		UnitOfMeasure teatpoonUom = teaSpoonUomOptional.get();
		UnitOfMeasure dashUom = dashUomOptional.get();
		UnitOfMeasure pintUom = pintUomOptional.get();
		UnitOfMeasure cupUom = cupUomOptional.get();
		
		// Obtenemos las categorias
		Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
		if(!americanCategoryOptional.isPresent()) {
			throw new RuntimeException("Categoria American No encontrada");
		}
		
		Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
		if(!mexicanCategoryOptional.isPresent()) {
			throw new RuntimeException("Categoria Mexican No encontrada");
		}
		
		Category americanCategory = americanCategoryOptional.get();
		Category mexicanCategory = mexicanCategoryOptional.get();
		
		Recipe guacamole = new Recipe();
		guacamole.setDescription("Guacamole Perfecto");
		guacamole.setPrepTime(10);
		guacamole.setCookTime(0);
		guacamole.setDifficulty(Difficulty.EASY);
		guacamole.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n"
				+ "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n"
				+ "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n"
				+ "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n"
				+ "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n"
				+ "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n"
				+ "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.");
		Notes notasGuacamole = new Notes();
		notasGuacamole.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n"
				+ "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n"
				+ "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n"
				+ "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n"
				+ "For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!");
		notasGuacamole.setRecipe(guacamole);
		guacamole.setNotes(notasGuacamole);
		guacamole.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
		guacamole.addIngredient(new Ingredient("Kosher salr", new BigDecimal(5), teatpoonUom));
		guacamole.addIngredient(new Ingredient("Fresh lim juic", new BigDecimal(2), tablespoonUom));
		guacamole.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), tablespoonUom));
		guacamole.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
		
		guacamole.getCategories().add(americanCategory);
		guacamole.getCategories().add(mexicanCategory);
		guacamole.setServings(4);
		guacamole.setSource("Elise Bauer");
		guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
		
		recipes.add(guacamole);
		
		Recipe chicken = new Recipe();
		chicken.setDescription("Spicy Grilled Chicken Taco");
		chicken.setPrepTime(20);
		chicken.setCookTime(9);
		chicken.setServings(6);
		chicken.setDifficulty(Difficulty.MEDIUM);
		chicken.setSource("Elise Bauer");
		chicken.setUrl("https://www.simplyrecipes.com/recipes/grilled_dukkah_crusted_chicken_with_lemon_hummus/");
		chicken.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n"
				+ "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n"
				+ "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n"
				+ "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n"
				+ "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n"
				+ "");
		chicken.getCategories().add(americanCategory);

		recipes.add(chicken);
				
		
		return recipes;
	}
}


