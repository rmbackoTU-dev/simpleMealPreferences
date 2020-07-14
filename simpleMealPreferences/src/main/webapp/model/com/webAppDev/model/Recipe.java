package com.webAppDev.model;

import java.util.ArrayList;

public class Recipe 
{

	//list of categories for recipes, recipes can only
	//belong to breakfast, lunch, dinner, or dessert
	public static final String[] RECIPE_CATEGORIES= {"breakfast",
			"lunch", "dinner", "dessert"}; 
	
	
	/**
	 * The initialization for the Recipe model
	 * the Ingredients and directions parameters will change once the models
	 * for directions and ingredients are added
	 * @param name
	 * @param ingredients
	 * @param directions
	 */
	
	
	public String recipeName;
	public String category;
	public ArrayList<Object> recipeIngredients;
	public ArrayList<String> recipeDirections;
	
	public Recipe(String name, ArrayList<Object> ingredients, ArrayList<String> directions,
			String category)
	throws IllegalArgumentException
	{
		if(name == null || ingredients == null || directions == null || name == "")
		{
			if(name != null)
			{
				System.out.println("Recieved name: "+name);
			}
			throw new IllegalArgumentException();
		}
		this.setCategory(category);
		this.recipeName=name;
		this.recipeIngredients=ingredients;
		this.recipeDirections=directions;
	}
	
	public void setCategory(String catagoryParam)
	{
		if(catagoryParam == "" || catagoryParam == null )
		{
			throw new IllegalArgumentException("Category"
					+ " can not be empty or null");
		}
		
		//Save unneeded computation by checking if the 
		//value is already set
		if(this.category != catagoryParam)
		{
			boolean found=false;
			int i=0;
			while(!found || i< RECIPE_CATEGORIES.length)
			{
				if(catagoryParam.equals(RECIPE_CATEGORIES[i]))
				{
					this.category=catagoryParam;
					found=true;
				}
				i=i+1;
				
			}
		
			if(!found)
			{
				throw new IllegalArgumentException("Category "
						+" must be either breakfast, lunch, dinner, "
						+" or dessert");
			}
		}
	}
	
	public String getCategory()
	{
		return this.category;
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
