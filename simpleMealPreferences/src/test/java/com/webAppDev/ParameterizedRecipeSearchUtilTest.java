package com.webAppDev;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;


import  com.webAppDev.model.RecipeUtils;
import  com.webAppDev.model.Recipe;


public class ParameterizedRecipeSearchUtilTest {
	
	private static ArrayList<Recipe> inputRecipeList;
	private static ArrayList<Recipe> outputRecipeList;
	private String searchTerm;
	private static int expectedResult;
	private static RecipeUtils recipeTools;
	
	public static char[] latinAlphabet= {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
	                                    'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
	                                    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
	                                    'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' '};
	
	
	@ParameterizedTest
	@CsvFileSource(resources="testAssets/searchUnitTestParameters.csv", numLinesToSkip=1)
	public void parameterizedTestSearchRecipesWithString(int searchSpaceSize, 
			int searchWordSize, int indexOfFirstFoundWord, int indexOfFirstCharMatch,
			int matchWordsSize, int numberOfMatches, int matchSpacing,
			int result)
	{
		/**
		 * expectedResult:
		 * 0 - normal execution (matches)
		 * 1 - normal execution (no matches)
		 * 2 - normal execution (no matches, search string larger than matches)
		 */
		expectedResult=result;
		HashMap<String, ArrayList<Recipe>> inputSpace=searchWordsFactory(searchSpaceSize, searchWordSize, indexOfFirstFoundWord, 
				indexOfFirstCharMatch, matchWordsSize, numberOfMatches, matchSpacing);
		for(Map.Entry<String, ArrayList<Recipe>> entry: inputSpace.entrySet())
		{
			searchTerm=entry.getKey();
			inputRecipeList=entry.getValue();
			outputRecipeList=resultListFactory(inputRecipeList,indexOfFirstFoundWord,
					matchSpacing, numberOfMatches);
            recipeTools=new RecipeUtils(outputRecipeList);
            if(expectedResult == 0)
            {
					System.out.println("Input Recipes: ");
					printRecipeNames(inputRecipeList);
					System.out.println("Output Recipes: ");
					printRecipeNames(outputRecipeList);
					//Assert the correct number of entries are returned
					recipeTools=new RecipeUtils(outputRecipeList);
					ArrayList<Recipe> recipeSearchResults=recipeTools.searchRecipesBySubString(searchTerm);
					Assert.assertEquals(outputRecipeList.size(), recipeSearchResults.size());
					Assert.assertEquals(outputRecipeList.get(0), recipeSearchResults.get(0));	
            }
            else if(expectedResult == 1)
            {
            	System.out.println("Using Search word: "+searchTerm);
            	System.out.println("Input Recipes: ");
            	printRecipeNames(inputRecipeList);
            	System.out.println("Output Recipes: ");
            	printRecipeNames(outputRecipeList);
            	//Assert the correct number of entries are returned
            	ArrayList<Recipe> recipeSearchResults=recipeTools.searchRecipesBySubString(searchTerm);
            	Assert.assertEquals(recipeSearchResults.size(), 0);
            }
            else if(expectedResult == 2)
            {
            	System.out.println("Using Search word: "+searchTerm);
            	System.out.println("Input Recipes: ");
            	printRecipeNames(inputRecipeList);
            	System.out.println("Output Recipes: ");
            	printRecipeNames(outputRecipeList);
            	//Assert the correct number of entries are returned
            	ArrayList<Recipe> recipeSearchResults=recipeTools.searchRecipesBySubString(searchTerm);
            	Assert.assertEquals(outputRecipeList.size(), 0);
            }
            else
            {
            	Assert.fail("Check input file, the result was not expected");
            }
		}
		//Run null search word test since we will only ever need 1
		
	}
	
	
	/**
	 * A function for checking illegalArgumentException
	 */
	@Test
	public void testIllegalArgumentException()
	{
		try
		{
				recipeTools.searchRecipesBySubString(null);
				Assert.fail("A null search string should throw a IllegalArgumentException");
		}
		catch(IllegalArgumentException ie)
		{
				Exception expectedException=new IllegalArgumentException("Search string can not be null");
				Assert.assertSame(expectedException, ie);
		}
	}
	
	/**
	 * Used to debug recipe names by printing out each one
	 * @param recipeList
	 */
	public static void printRecipeNames(ArrayList<Recipe> recipeList)
	{
		Recipe currentRecipe;
		for(Recipe recipeEntry: recipeList)
		{
			currentRecipe=recipeEntry;
			System.out.println(currentRecipe.getRecipeName());
		}
	}
	
	
	/**
	 * Converts a list of Strings to a list of RecipeNames
	 * @param RecipeNames
	 * @return
	 */
	public static ArrayList<Recipe> initializeRecipeListWithInputList(ArrayList<String> RecipeNames)
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
	
	/**
	 * Factory function generates a list of research space results based on
	 * Initial parameters
	 * @param searchWords
	 * @param indexOfFirstFoundWord
	 * @param matchSpacing
	 * @param numberOfMatches
	 * @return
	 */
	public static ArrayList<Recipe> resultListFactory(ArrayList<Recipe> searchWords, 
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
	 * 
	 * based on a prime path based graph coverage testing model
	 * @param searchSpaceSize
	 * @param searchWordSize
	 * @param indexOfFirstFoundWord
	 * @param indexOfFirstCharacterMatch has different meanings depending on expectedResult
	 * 		when expectedResult=0 first character matches start of search word, when expectedResult=1
	 *      first  character does not contain the character 
	 *      in the latin alphabet found at the indexOfFirstCharacter match
	 * @param matchWordSize has different meanings depending on expectedResult
	 * 		when expectedResult=0 matchWordSize is the size of the first word that matches
	 * 		when expectedResult=1 matchWordSize is the size of all words in the input space
	 * @Param numberOfMatches
	 * @Param matchSpacing
	 * @return All necessary search test parameters
	 */
	public  static HashMap<String, ArrayList<Recipe>> searchWordsFactory
	(int searchSpaceSize, int searchWordSize, int indexOfFirstFoundWord, int indexOfFirstCharMatch,
			int matchWordsSize, int numberOfMatches, int matchSpacing)
	{
		String searchWord;
		ArrayList<String> searchSpace;
		ArrayList<Recipe> recipeSearchSpace;
		//For expectedResult 0 indexOfFirstCharMatch is the first character to match
		//For expectedResult 1 indexOfFirstCharMatch is the first character to not match
		HashMap<String, ArrayList<Recipe>> inputSpace=new HashMap<String, ArrayList<Recipe>>();
		char firstCharacter=latinAlphabet[indexOfFirstCharMatch%latinAlphabet.length];
		if(expectedResult == 0 || expectedResult == 2 )
		{
				searchWord=makeSearchString(searchWordSize, firstCharacter);
				System.out.println("Search WORD: "+searchWord);
				searchSpace=generateInputSpace(searchSpaceSize, indexOfFirstFoundWord,
						searchWord, indexOfFirstCharMatch, matchWordsSize, numberOfMatches, matchSpacing);
		}
		else
		{
				System.out.println("Enter section");
				searchWord=makeNoMatchingSearchString(searchWordSize, firstCharacter);
				System.out.println("Search WORD: "+searchWord);
				searchSpace=generateNonMatchingInputSpace(searchSpaceSize, searchWord, matchWordsSize,
						firstCharacter);
		}
		recipeSearchSpace=initializeRecipeListWithInputList(searchSpace);
		inputSpace.put(searchWord, recipeSearchSpace);
		return inputSpace;
	}
	
	/**
	 * Make a search word based on a starting letter and search size
	 * @param searchWordSize
	 * @param firstCharacter
	 * @return a search string
	 */
	public  static String makeSearchString(int searchWordSize, char firstCharacter)
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
	
	
	/** Make a search word that does not have the starting letter
	 * @param searchWordSize
	 * @param firstCharToAvoid
	 * @return A search string that does not match.
	 */
	public static String makeNoMatchingSearchString(int searchWordSize, char firstCharToAvoid) 
	{
		System.out.print("The maximum search string size is "+ searchWordSize);
		char[] searchWord=new char[searchWordSize];
		int stringSize=0;
		int i=0;
		while(stringSize< searchWordSize)
		{
			if((latinAlphabet[i] != firstCharToAvoid))
			{
				//using a char array instead of a string optimizes performance since we do
				//not have to keep allocating memory
				searchWord[stringSize]=latinAlphabet[i];
				stringSize=stringSize+1;
				System.out.println("the current String size is "+stringSize);
			}
			i=i+1;
		}
		return String.valueOf(searchWord);
	}
	
	/**
	 * Create a list of Recipe Names without the search word
	 * @param searchSpaceSize use to state the size of the words searched
	 * @param searchWord use to state the search Word to avoid
	 * @param wordSize use to state the size of the words in the list
	 * @param charToAvoid, the character to avoid adding to the inputSpace
	 * @return
	 */
	public static ArrayList<String> generateNonMatchingInputSpace(int searchSpaceSize, String searchWord,
			int wordSize, char charToAvoid)
	{
		System.out.println("The Character to avoid is "+charToAvoid);
		int a=0;
		ArrayList<String> stringSearchSpace=new ArrayList<String>();
		while(a<searchSpaceSize)
		{
			char currentChar;
			int c=0;
			int charNum=0;
			String currentSearchString;
			char[] entryWord=new char[wordSize];
			while(charNum < wordSize)
			{
				currentChar=latinAlphabet[c%latinAlphabet.length];
				if(currentChar != charToAvoid)
				{
					entryWord[charNum]=currentChar;
					charNum=charNum+1;
				}
				c=c+1;
				
			}
			currentSearchString=String.valueOf(entryWord);
			stringSearchSpace.add(currentSearchString);
			a=a+1;
		}
		return stringSearchSpace;
	}
	
	/**
	 * Makes a space to search which will perform graph based testing on the recipe search function
	 * Use for a minimum numberOfMatches of 1 or higher
	 * @param searchSpeaceSize
	 * @param indexOfFirstFoundWord
	 * @param searchWord
	 * @param numberOfMatches
	 * @param matchSpacing
	 */
	public static ArrayList<String> generateInputSpace(int searchSpaceSize, int indexOfFirstFoundWord,
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
	
	/**
	 * Used to calculate the GCD needed to make a search space.
	 * @param a
	 * @param b
	 * @return
	 */
	public static int gcd(int a, int b)
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
