package com.webAppDev;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import com.webAppDev.model.Recipe;
import com.webAppDev.model.RecipeUtils;

public class RecipeUtilTest {

	private ArrayList<Recipe> recipeList;
	private RecipeUtils recipeTools;
	private String testCategory;
	
	@Test
	public void testZeroItemZeroMatchCategoryMatch()
	{
		recipeList=new ArrayList<Recipe>();
		recipeTools=new RecipeUtils(recipeList);
		testCategory="";
		ArrayList<Recipe> outputRecipeList=
				recipeTools.searchRecipesByCategory(testCategory);
		Assertions.assertEquals(0, outputRecipeList.size());
	}
	
	
	@Test
	public void testOneItemZeroMatchCategoryMatch()
	{
		recipeList=new ArrayList<Recipe>();
		Recipe testRecipe=new Recipe("Test-Recipe-One",
				new ArrayList<Object>(), new ArrayList<String>(),
				"breakfast");
		recipeList.add(testRecipe);
		recipeTools=new RecipeUtils(recipeList);
		testCategory="";
		ArrayList<Recipe> outputRecipeList=
				recipeTools.searchRecipesByCategory(testCategory);
		Assertions.assertEquals(0, outputRecipeList.size());
	}
	
	@Test
	public void testTwoItemZeroMatchCategoryMatch()
	{
		recipeList=new ArrayList<Recipe>();
		Recipe testRecipe=new Recipe("Test-Recipe-One",
				new ArrayList<Object>(), new ArrayList<String>(),
				"breakfast");
		recipeList.add(testRecipe);
		Recipe testRecipeTwo=new Recipe("Test-Recipe-Two",
				new ArrayList<Object>(), new ArrayList<String>(),
				"lunch");
		recipeList.add(testRecipeTwo);
		recipeTools=new RecipeUtils(recipeList);
		testCategory="";
		ArrayList<Recipe> outputRecipeList=
				recipeTools.searchRecipesByCategory(testCategory);
		Assertions.assertEquals(0, outputRecipeList.size());
	}
	
	
	@Test
	public void testOneItemOneMatchCategoryMatch()
	{
		recipeList=new ArrayList<Recipe>();
		Recipe testRecipe=new Recipe("Test-Recipe-One",
				new ArrayList<Object>(), new ArrayList<String>(),
				"breakfast");
		recipeList.add(testRecipe);
		recipeTools=new RecipeUtils(recipeList);
		testCategory="breakfast";
		ArrayList<Recipe> outputRecipeList=
				recipeTools.searchRecipesByCategory(testCategory);
		Assertions.assertEquals(1, outputRecipeList.size());
	}
	
	@Test
	public void testTwoItemOneMatch()
	{
		recipeList=new ArrayList<Recipe>();
		Recipe testRecipe=new Recipe("Test-Recipe-One",
				new ArrayList<Object>(), new ArrayList<String>(),
				"breakfast");
		recipeList.add(testRecipe);
		Recipe testRecipeTwo=new Recipe("Test-Recipe-Two",
				new ArrayList<Object>(), new ArrayList<String>(),
				"lunch");
		recipeList.add(testRecipeTwo);
		recipeTools=new RecipeUtils(recipeList);
		testCategory="breakfast";
		ArrayList<Recipe> outputRecipeList=
				recipeTools.searchRecipesByCategory(testCategory);
		Assertions.assertEquals(1, outputRecipeList.size());
	}
	
	@Test
	public void testTwoItemTwoMatch()
	{
		recipeList=new ArrayList<Recipe>();
		Recipe testRecipe=new Recipe("Test-Recipe-One",
				new ArrayList<Object>(), new ArrayList<String>(),
				"breakfast");
		recipeList.add(testRecipe);
		Recipe testRecipeTwo=new Recipe("Test-Recipe-Two",
				new ArrayList<Object>(), new ArrayList<String>(),
				"breakfast");
		recipeList.add(testRecipeTwo);
		recipeTools=new RecipeUtils(recipeList);
		testCategory="breakfast";
		ArrayList<Recipe> outputRecipeList=
				recipeTools.searchRecipesByCategory(testCategory);
		Assertions.assertEquals(2, outputRecipeList.size());
	}
}
