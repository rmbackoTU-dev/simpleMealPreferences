package com.webAppDev.model;

import java.util.ArrayList;

public class RecipeUtils {
	
	public ArrayList<Recipe> recipeList;
	
	public RecipeUtils(ArrayList<Recipe> recipeListParam)	{
		this.recipeList=recipeListParam;
	}
	/**
	 * Performs a search for recipes in a category.
	 * @param category
	 * @return A list of recipes that match the category
	 */
	public ArrayList<Recipe> searchRecipesByCategory(String category)
	{
		ArrayList<Recipe> resultsList=new ArrayList<Recipe>();
		Recipe currentRecipe;
		for(int i=0; i<recipeList.size(); i++)
		{
			currentRecipe=recipeList.get(i);
			if(currentRecipe.getCategory() == category)
			{
				resultsList.add(currentRecipe);
			}
		}
		return resultsList;
	}
	
	/**
	 * Performs a left to right string search. If a substring is found
	 * in a recipe name the recipe is added to the recipe list
	 * @param subString
	 * @return A list of recipes which has the contained substring
	 * from left to right
	 */
	public ArrayList<Recipe> searchRecipesBySubString(String subString)
	{
		ArrayList<Recipe> resultsList=new ArrayList<Recipe>();
		if(subString == null)
		{
			throw new IllegalArgumentException("Search string can not be null");
		}
		int subStringLength=subString.length();
		//select a candidate string first
		for(int i=0; i<this.recipeList.size(); i++)
		{
			Recipe recipeCandidate=recipeList.get(i);
			String candidateName=recipeCandidate.getRecipeName();
			int recipeCandidateNameSize=candidateName.length();
			int stringDiff=recipeCandidateNameSize-subStringLength;
			//Increment characters until incrementing the difference between the
			//searchWord and found word. If  the string is at the sub string length 
			//it really should not start, and just return 0.
			for(int j=0; j<=stringDiff; j++)
			{
				int k=0;
				while(k< subStringLength &&
						(candidateName.charAt(j+k) ==
						subString.charAt(k)))
				{
					k=k+1;
				}
				if(k >= subStringLength)
				{
					resultsList.add(recipeCandidate);
				}
			}
			
		}
		return resultsList;
	}
}
