package com.webAppDev;

import java.lang.Thread;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import  com.webAppDev.model.Recipe;



public class RecipeTest {
	
	Recipe testRecipe;
	
//	@BeforeEach
//	public void setup()
//	{
//		
//	}
//	
//	@AfterEach
//	public void teardown()
//	{
//		
//	}
	
	
	public void initializeRecipeDefaults()
	{
		String recipeName="Recipe One";
		String breakfastCategory="breakfast";
		ArrayList<Object> ingredientList=new ArrayList<Object>();
		ArrayList<String> directionList=new ArrayList<String>();
		this.testRecipe=new Recipe(recipeName, ingredientList, directionList,
				breakfastCategory);
	}
	
	/**
	 * Test that a recipe is initializable 
	 * with recipe name, ingredients, and directions
	 */
	@Test
	public void testInitialization()
	{
		String recipeName="Recipe One";
		String breakfastCategory="breakfast";
		ArrayList<Object> ingredientList=new ArrayList<Object>();
		ArrayList<String> directionList=new ArrayList<String>();
		Recipe testRecipe=new Recipe(recipeName, ingredientList, directionList,
				breakfastCategory);
		Assert.assertEquals("Recipe  Model Class", testRecipe.toString());
	}
	
	
	/**
	 * Test initialization of recipe with null IngredientList
	 */
	@Test
	public void testNullIngredientsInitilization()
	{
		String recipeName="Recipe One";
		String breakfastCategory="breakfast";
		ArrayList<Object> nullIngredientList=null;
		ArrayList<String> directionList=new ArrayList<String>();
		try
		{
			Recipe testRecipe=new Recipe(recipeName, nullIngredientList, directionList,
					breakfastCategory);
			Assert.fail("Recipe initialized with null directions should throw"
					+ " IllegalArgumentException");
		}
		catch(IllegalArgumentException ie)
		{
			Assert.assertTrue(ie instanceof IllegalArgumentException);
		}
	}
	
	
	/**
	 * Test initialization of recipe with null IngredientList
	 */
	@Test
	public void testNullDirectionsInitilization()
	{
		String recipeName="Recipe One";
		String breakfastCategory="breakfast";
		ArrayList<Object> ingredientList=new ArrayList<Object>();
		ArrayList<String> nullDirectionList=null;
		try
		{
			Recipe testRecipe=new Recipe(recipeName, ingredientList, nullDirectionList,
					breakfastCategory);
			Assert.fail("Recipe initialized with null directions should throw"
					+ " IllegalArgumentException");
		}
		catch(IllegalArgumentException ie)
		{
			Assert.assertTrue(ie instanceof IllegalArgumentException);
		}
	}
	
	
	/**
	 * Test that a recipe provide null parameters
	 * throws a IllegalArgumentException
	 */
	@Test
	public void testNullInitialization()
	{
		String nullName=null;
		String nullCategory=null;
		ArrayList<Object> nullIngredients=null;
		ArrayList<String> nullDirections=null;
		try
		{
			Recipe testRecipeInitialized=new Recipe(nullName, nullIngredients, nullDirections, nullCategory);
			Assert.fail("Function should throw IllegalArgumentException");
		}
		catch(IllegalArgumentException ie)
		{
			Assert.assertTrue(ie instanceof IllegalArgumentException);
		}
	}
	
	/**
	 * Test that a recipe provided with a empty string
	 * for recipe name throws a IllegalArgumentException
	 */
	@Test
	public void testEmptyRecipeNameInitialization()
	{
		String breakfastCategory="breakfast";
		ArrayList<Object> ingredientList=new ArrayList<Object>();
		ArrayList<String> directionList=new ArrayList<String>();
		try
		{
			Recipe testRecipeInitialized=new Recipe("", ingredientList, directionList, 
					breakfastCategory );
			Assert.fail("Function should throw IllegalArgumentException");
		}
		catch(IllegalArgumentException ie)
		{
			Assert.assertTrue(ie instanceof IllegalArgumentException);
		}
	}
	
	
	/**
	 * Test a user/servlet is able to retrieve the current name 
	 * of a recipe
	 */
	@Test
	public void testGetRecipeName()
	{
		this.initializeRecipeDefaults();
		Assert.assertEquals("Recipe One", this.testRecipe.getRecipeName());
		
	}

	/**
	 * Test that a recipe name is setable for a recipe
	 */
	@Test
	public void testSetRecipeName()
	{
		this.initializeRecipeDefaults();
		this.testRecipe.setRecipeName("Recipe Two");
		Assert.assertEquals("Recipe Two", this.testRecipe.getRecipeName());
	}
	
	/**
	 * Test that if a null parameter is passed to setRecipeName
	 * that a IllegalArgumentException
	 */
	@Test
	public void testSetNullRecipeName()
	{
		this.initializeRecipeDefaults();
		try
		{
			this.testRecipe.setRecipeName(null);
			Assert.fail("Function should throw an IllegalArgumentException");
		}
		catch(IllegalArgumentException ie)
		{
			Assert.assertTrue(ie instanceof IllegalArgumentException);
		}
	}
	
	/**
	 * Test that if a empty string is passed to setRecipeName
	 * that a IllegalArgumentException is thrown
	 */
	@Test
	public void testSetEmptyRecipeName()
	{
		this.initializeRecipeDefaults();
		try
		{
			this.testRecipe.setRecipeName("");
			Assert.fail("Function should throw an IllegalArgumentException");
		}
		catch(IllegalArgumentException ie)
		{
			Assert.assertTrue(ie instanceof IllegalArgumentException);
		}
	}
	
	/**
	 * Test that when a recipe is set to breakfast it will change the category if
	 * set to lunch
	 */
	@Test
	public void testSetLunchWithBreakfastPreset()
	{
		this.initializeRecipeDefaults();
		testRecipe.setCategory("lunch");
		String testCategory=testRecipe.getCategory();
		Assert.assertEquals("lunch", testCategory);
	}
	
	
	/**
	 * Test that when a recipe is set to breakfast it will change the category if
	 * set to dinner
	 */
	@Test
	public void testSetDinnerWithBreakfastPreset()
	{
		this.initializeRecipeDefaults();
		testRecipe.setCategory("dinner");
		String testCategory=testRecipe.getCategory();
		Assert.assertEquals("dinner", testCategory);
	}
	
	/**
	 * Test that when a recipe is set to breakfast it will change the category if
	 * set to dinner
	 */
	@Test
	public void testSetDesertWithBreakfastPreset()
	{
		this.initializeRecipeDefaults();
		testRecipe.setCategory("dessert");
		String testCategory=testRecipe.getCategory();
		Assert.assertEquals("dessert", testCategory);
	}
	
	/**
	 * Test that when a recipes already set to lunch it will not change
	 * if set lunch
	 */
	@Test
	public void testSetLunchwithLunchPreset()
	{
		String lunchCategory="lunch";
		String recipeName="lunchTest";
		ArrayList<Object> IngredientList=new ArrayList<Object>();
		ArrayList<String> DirectionList=new ArrayList<String>();
		testRecipe=new Recipe(recipeName, IngredientList, DirectionList, lunchCategory);
		testRecipe.setCategory(lunchCategory);
		String testCategory=testRecipe.getCategory();
		Assert.assertEquals("lunch", testCategory );
	}
	
	/**
	 * Test that when a recipes already set to dinner it will not change
	 * if set lunch
	 */
	@Test
	public void testSetDinnerwithDinnerPreset()
	{
		String dinnerCategory="dinner";
		String recipeName="dinnerTest";
		ArrayList<Object> IngredientList=new ArrayList<Object>();
		ArrayList<String> DirectionList=new ArrayList<String>();
		testRecipe=new Recipe(recipeName, IngredientList, DirectionList, dinnerCategory);
		testRecipe.setCategory(dinnerCategory);
		String testCategory=testRecipe.getCategory();
		Assert.assertEquals("dinner", testCategory );
	}
	
	/**
	 * Test that when a recipes already set to dinner it will not change
	 * if set lunch
	 */
	@Test
	public void testSetDinnerwithDessertPreset()
	{
		String dinnerCategory="dessert";
		String recipeName="dessertTest";
		ArrayList<Object> IngredientList=new ArrayList<Object>();
		ArrayList<String> DirectionList=new ArrayList<String>();
		testRecipe=new Recipe(recipeName, IngredientList, DirectionList, dinnerCategory);
		testRecipe.setCategory(dinnerCategory);
		String testCategory=testRecipe.getCategory();
		Assert.assertEquals("dessert", testCategory );
	}
	
	/**
	 * Test that when a recipe is already set to breakfast it will not change
	 * if set breakfast is choosen
	 */
	@Test
	public void testSetBreakfastWithBreakfastPreset()
	{
		this.initializeRecipeDefaults();
		testRecipe.setRecipeName("breakfast");
		String testCategory=testRecipe.getCategory();
		Assert.assertEquals("breakfast", testCategory);
	}
	
	/**
	 * Test that a user/servlet is able to retrieve the current list of
	 * ingredients for a recipe
	 */
	@Test
	public void testGetIngredients()
	{
		Assert.fail("To be implemented with ingredient class");
	}
	
	/**
	 * Test that a list of ingredients can be set for a 
	 * given recipe
	 */
	@Test
	public void testSetIngredientList()
	{
		Assert.fail("To be implemented with ingredient class");
	}
	
	/**
	 * Test that if a null list of ingredients is passed that
	 * that a IllegalArgumentException is thrown
	 */
	@Test
	public void testSetNullIngredientList()
	{
		Assert.fail("To be implemented with ingredient class");
	}
	
	/**
	 * Test that user/servlet is able to retrieve the current direction for
	 * a recipe
	 */
	@Test
	public void testGetDirections()
	{
		Assert.fail("To be implemented with directions class");
	}
	
	
	/**
	 * Test that user is able to successfully set a list of 
	 * directions for a recipe
	 */
	@Test
	public void testSetDirectionsList()
	{
		Assert.fail("To be implemented with directions class");
	}
	
	/**
	 * Test that if a null direction list is set that a 
	 * IllegalArgumentException is thrown
	 */
	@Test
	public void testSetNullDirectionsList()
	{
		Assert.fail("To be implemented with directions class");
	}
	
	/**
	 * Test that a recipe successful returns the number of 
	 * ingredients in a given recipe
	 */
	@Test
	public void testGetIngredientCount()
	{
		this.initializeRecipeDefaults();
		Assert.assertEquals(0, this.testRecipe.getIngredientCount());
	}
	
	/**
	 * Test that a recipe successfully returns number of 
	 * steps in a given recipe
	 */
	@Test
	public void testGetDirectionCount()
	{
		this.initializeRecipeDefaults();
		Assert.assertEquals(0, this.testRecipe.getDirectionCount());
	}
	
	/**
	 * Todo:
	 * Test that getDirectionCount, and getIngredientCount work for more than 1, and 2 directions and ingredients
	 * once directions and ingredient model classes are implemented.
	 */
}
