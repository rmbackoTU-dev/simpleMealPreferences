package com.webAppDev.model;

import java.util.ArrayList;

public class Recipe 
{

	/**
	 * The initialization for the Recipe model
	 * the Ingredients and directions parameters will change once the models
	 * for directions and ingredients are added
	 * @param name
	 * @param ingredients
	 * @param directions
	 */
	
	public String recipeName;
	public ArrayList<Object> recipeIngredients;
	public ArrayList<String> recipeDirections;
	
	public Recipe(String name, ArrayList<Object> ingredients, ArrayList<String> directions)
	throws IllegalArgumentException
	{
		if(name == null || ingredients == null || directions == null || name == "")
		{
			throw new IllegalArgumentException();
		}
		this.recipeName=name;
		this.recipeIngredients=ingredients;
		this.recipeDirections=directions;
	}
	
	public String getRecipeName()
	{
		return this.recipeName;
	}
	
	public void setRecipeName(String newRecipeName)
	throws IllegalArgumentException
	{
		if(newRecipeName == null || newRecipeName == "")
		{
			throw new IllegalArgumentException();
		}
		this.recipeName=newRecipeName;
	}
	
	public int getIngredientCount()
	{
		return this.recipeIngredients.size();
	}
	
	public int getDirectionCount()
	{
		return this.recipeDirections.size();
	}
	
	@Override
	public String toString()
	{
		return "Recipe  Model Class";
	}
	
	
}
