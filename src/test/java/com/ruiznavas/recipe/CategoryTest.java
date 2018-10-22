package com.ruiznavas.recipe;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ruiznavas.recipe.domain.Category;

public class CategoryTest {
	Category category;
	
	@Before
	public void setUp() {
		category = new Category();
	}

	@Test
	public void testGetId() {
		Long idValue = 4l;
		category.setId(idValue);
		assertEquals(idValue, category.getId());
	}

	@Test
	public void testGetDescription() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRecipes() {
		fail("Not yet implemented");
	}

}
