package com.webAppDev;

import java.lang.Thread;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;



import  com.webAppDev.model.RecipeUtils;
import  com.webAppDev.model.Recipe;

public class ParameterizedRecipeSearchUtilTest {

	
	public static char[] latinAlphabet= {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
	                                    'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
	                                    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
	                                    'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' '};
	@ParameterizedTest
	@CsvFileSource(resources= "testAssets/searchUnitTestParameters.csv", numLinesToSkip=1)
	public void parameterizedTestSearchRecipesWithString(int searchSpaceSize, 
			int searchWordSize, int indexOfFirstFoundWord, int indexOfFirstCharMatch,
			int matchWordsSize, int numberOfMatches, int matchSpacing,
			int expectedResult)
	{
		/**
		 * expectedResult:
		 * 0 - normal execution (matches)
		 * 1 - normal execution (no matches)
		 * 2 - null/invalid input exception
		 */
		if(expectedResult == 0)
		{
			ArrayList<Recipe> testRecipeArrayInput;
			HashMap<String, ArrayList<Recipe>> inputSpace;
			ArrayList<Recipe> testRecipeArrayOutput;
			inputSpace=searchWordsFactory(searchSpaceSize, searchWordSize, indexOfFirstFoundWord, 
					indexOfFirstCharMatch, matchWordsSize, numberOfMatches, matchSpacing);
			for(Map.Entry<String, ArrayList<Recipe>> entry: inputSpace.entrySet())
			{
				String searchWord=entry.getKey();
				testRecipeArrayInput=entry.getValue();
				testRecipeArrayOutput=resultListFactory(testRecipeArrayInput,indexOfFirstFoundWord,
						matchSpacing, numberOfMatches);
				System.out.println("Using Search word: "+searchWord);
				System.out.println("Input Recipes: ");
				printRecipeNames(testRecipeArrayInput);
				System.out.println("Output Recipes: ");
				printRecipeNames(testRecipeArrayOutput);
				//Assert the correct number of entries are returned
				RecipeUtils testUtils=new RecipeUtils(testRecipeArrayOutput);
				ArrayList<Recipe> recipeSearchResults=testUtils.searchRecipesBySubString(searchWord);
				Assert.assertEquals(testRecipeArrayOutput.size(), recipeSearchResults.size());
				Assert.assertEquals(testRecipeArrayOutput.get(0), recipeSearchResults.get(0));
			}
			
			
		}
		else if(expectedResult == 1)
		{
			Assert.fail("Not yet implemented");
		}
		else if(expectedResult == 2)
		{
			Assert.fail("Not yet implemented");
		}
		else
		{
			Assert.fail("Check input file, the result was not expected");
		}
	}
	
	
	public void printRecipeNames(ArrayList<Recipe> recipeList)
	{
		Recipe currentRecipe;
		for(Recipe recipeEntry: recipeList)
		{
			currentRecipe=recipeEntry;
			System.out.println(currentRecipe.getRecipeName());
		}
	}
	
	public ArrayList<Recipe> initializeRecipeListWithInputList(ArrayList<String> RecipeNames)
	{
		//Use defaults for recipe object since RecipeName search is under test
		ArrayList<String> emptyDirections=new ArrayList<String>();
		ArrayList<Object> emptyIngredients=new ArrayList<Object>();
		String breakfastCategory="breakfast";
		//Return object is a list of recipe object with the same 
		//ingredients, directions, and category, but different names
		ArrayList<Recipe> recipeList=new ArrayList<Recipe>();
		Recipe currentRecipe;
		for(int i=0; i<RecipeNames.size(); i++)
		{
			currentRecipe=new Recipe((String) RecipeNames.get(i), emptyIngredients, 
					emptyDirections, breakfastCategory);
			recipeList.add(currentRecipe);
		}
		
		return recipeList;
	}
	
	public ArrayList<Recipe> resultListFactory(ArrayList<Recipe> searchWords, 
			int indexOfFirstFoundWord, int matchSpacing, int numberOfMatches)
	{
		ArrayList<Recipe> recipeList=new ArrayList<Recipe>();
		int j=indexOfFirstFoundWord;
		Recipe currentWord;
		for(int i=0; i< numberOfMatches; i++)
		{
			currentWord=searchWords.get(indexOfFirstFoundWord);
			recipeList.add(currentWord);
			j=j+matchSpacing;
		}
		return recipeList;
	}
	
	/**
	 * Factory function generates a list of search spaces, search words,
	 * and expected results to feed to a parameterized test
	 * based on a prime path based graph coverage testing model
	 * @param searchSpaceSize
	 * @param searchWordSize
	 * @param indexOfFirstFoundWord
	 * @param indexOfFirstCharacterMatch
	 * @Param numberOfMatches
	 * @Param matchSpacing
	 * @return 
	 */
	public HashMap< String, ArrayList<Recipe>> searchWordsFactory
	(int searchSpaceSize, int searchWordSize, int indexOfFirstFoundWord, int indexOfFirstCharMatch,
			int matchWordsSize, int numberOfMatches, int matchSpacing)
	{
		String searchWord;
		ArrayList<String> searchSpace;
		ArrayList<Recipe> recipeSearchSpace;
		HashMap<String, ArrayList<Recipe>> inputSpace=new HashMap<String, ArrayList<Recipe>>();
		char firstCharacter=latinAlphabet[indexOfFirstCharMatch%latinAlphabet.length];
		searchWord=makeSearchString(searchWordSize, firstCharacter);
		searchSpace=generateInputSpace(searchSpaceSize, indexOfFirstFoundWord,
				searchWord, indexOfFirstCharMatch, matchWordsSize, numberOfMatches, matchSpacing);
		recipeSearchSpace=initializeRecipeListWithInputList(searchSpace);
		inputSpace.put(searchWord, recipeSearchSpace);
		return inputSpace;
		
	}
	
	public String makeSearchString(int searchWordSize, char firstCharacter)
	{
		String searchString="";
		int i=0;
		boolean found=false;
		//find start of searchString
		while(i< latinAlphabet.length && !found)
		{	
			if(firstCharacter == latinAlphabet[i])
			{
				found=true;
			}
			else
			{
				i=i+1;
			}
		}
		int lastIndex=i+searchWordSize;
		while(i < lastIndex)
		{
			searchString=searchString+latinAlphabet[i];
		    i=i+1;
		}
		System.out.println("searchString: "+searchString);
		return searchString;
	}
	
	public ArrayList<String> generateInputSpace(int searchSpaceSize, int indexOfFirstFoundWord,
			String searchWord, int indexOfFirstCharMatch, 
			int matchWordSize, int numberOfMatches, int matchSpacing)
	{
		ArrayList<String> inputSpace=new ArrayList<String>();
		char avoidLetter=searchWord.charAt(0);
		System.out.println("Avoid Letter: "+avoidLetter);
		boolean matchEntry;
		int matchesAdded=0;
		if(indexOfFirstFoundWord == 0)
		{
			matchEntry=true;
		}
		else
		{
			matchEntry=false;
		}
		int a=0;
		//loop generates a list of words which match the parameters.
		while(a<searchSpaceSize)
		{
			//generate words
			char currentChar;
			String entryWord="";
			System.out.println("Adding matching entry "+matchEntry);
			if(matchEntry == false)
			{
				int c=0;
				int charNum=0;
				while(charNum < matchWordSize)
				{
					currentChar=latinAlphabet[c%latinAlphabet.length];
					if(currentChar != avoidLetter)
					{
						entryWord=entryWord+currentChar;
						charNum=charNum+1;
						
					}
					c=c+1;
				}		
			}
			else
			{
				int e=0;
				int p=0;
				while(e < matchWordSize)
				{
					currentChar=latinAlphabet[p%latinAlphabet.length];
					System.out.println("Current letter: "+currentChar);
					//avoid the first letter of the search word until correct
					//character index is reached
					if(e< indexOfFirstCharMatch)
					{
						if(currentChar != avoidLetter)
						{
								entryWord=entryWord+currentChar;
								System.out.println("Adding letter before index of first match @"
										+indexOfFirstCharMatch);
						}
						e=e+1;
					}
					//at the first letter of the match add the searchWord to the string
					else if(e >= indexOfFirstCharMatch && e <= indexOfFirstCharMatch+matchWordSize)
					{
						entryWord=entryWord+searchWord;
						System.out.println("Entry word="+entryWord);
						e=e+searchWord.length();
						System.out.println("Adding letter at index of first match @"
								+indexOfFirstCharMatch);
					}
					else if(e > indexOfFirstCharMatch+matchWordSize)
					{
						entryWord=entryWord+currentChar;
						System.out.println("Adding letter after index of first match @"
								+indexOfFirstCharMatch);
					}
					p=p+1;
				}
				matchesAdded=matchesAdded+1;
			}
			inputSpace.add(entryWord);
			a=a+1;
			if(a >= indexOfFirstFoundWord && matchesAdded < numberOfMatches )
			{
				if(matchSpacing > 0)
				{
					//gcd between the firstMatch and space between matches
					//this allows us to set match entry on increment == indexOfFirstFoundWord + matchSpacing % gcd
					int matchGCD;
					if(indexOfFirstFoundWord > matchSpacing)
					{
						matchGCD=gcd(indexOfFirstFoundWord, matchSpacing);
					}
					else
					{
						matchGCD=gcd(matchSpacing, indexOfFirstFoundWord);
					}
					System.out.println("matchGCD = "+matchGCD);
					if((a == (indexOfFirstFoundWord+matchSpacing)%matchGCD))
					{
						matchEntry=true;
					}
					else
					{
					matchEntry=false;
					}
				}
				else
				{
					matchEntry=true;
				}
			}
			else
			{
				matchEntry=false;
			}
			
			System.out.println("a iter val: "+a);
			System.out.println("searchSpaceSize: "+searchSpaceSize);
		}
		
		
		
		return inputSpace;
	}
	
	public int gcd(int a, int b)
	{
		//a=bq+r
		/*
		 * a=3, b=2
		 * 3 % 2 =1
		 * 1 !=0 -> a=2, b=1
		 * 2 % 1= 1 
		 * 1 != 0-> a=1, b=1
		 * 1 %1 == 0
		 * 0== 0 -> b
		 */
		
		if(b == 0)
		{
			return a ;
		}
		else
		{
			return gcd(b,(a%b));
		}
		
	}
}
