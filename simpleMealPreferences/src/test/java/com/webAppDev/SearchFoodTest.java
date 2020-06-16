package com.webAppDev;


import java.lang.Thread;
import java.util.concurrent.TimeUnit;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterEach;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeDriver;


public class SearchFoodTest {
	
	//Default sleep time should be .5 seconds
	public static final long SLEEP_TIME=500;
		
	//note these test at a minimum need FirefoxDrivers, and ChromeDrivers to run
	//IEDriver, EdgeDriver, Webkit Drivers. Are planned for future releases.
	//integration with a CI/CT server may require use of headless webDrivers
	
	WebDriver fireFoxDriver;
	WebDriver chromeDriver;
	
	@AfterEach
	public void teardown()
	{
		System.out.println("Running tear-down");
		fireFoxDriver.quit();
		chromeDriver.quit();
	}

	@Test
	public void testMainHeading() {
		WebElement heading;
		//Start Title test for fireFox
		parameterSetupFireFox();
		fireFoxDriver.get("http://localhost:8080/simpleMealPreferences/mealCategoryForm.html");
		heading=fireFoxDriver.findElement(By.id("mainHeading"));
		Assert.assertEquals("Search Meals", heading.getText());
		//Start Title test for chrome
		parameterSetupChrome();
		chromeDriver.get("http://localhost:8080/simpleMealPreferences/mealCategoryForm.html");
		heading=chromeDriver.findElement(By.id("mainHeading"));
		Assert.assertEquals("Search Meals", heading.getText());
	}
	
	@Test
	public void testFormParagraph() {
		WebElement formParagraphOne;
		parameterSetupFireFox();
		fireFoxDriver.get("http://localhost:8080/simpleMealPreferences/mealCategoryForm.html");
		formParagraphOne=fireFoxDriver.findElement(By.id("pageDescription"));
		Assert.assertEquals("Select meal category.", formParagraphOne.getText());
		parameterSetupChrome();
		chromeDriver.get("http://localhost:8080/simpleMealPreferences/mealCategoryForm.html");
		formParagraphOne=chromeDriver.findElement(By.id("pageDescription"));
		Assert.assertEquals("Select meal category.", formParagraphOne.getText());
	}
	
	@Test
	public void testFormLabel() {
		WebElement formLabel;
		parameterSetupFireFox();
		fireFoxDriver.get("http://localhost:8080/simpleMealPreferences/mealCategoryForm.html");
		formLabel=fireFoxDriver.findElement(By.id("meal-type-label"));
		Assert.assertEquals("Meal-type:", formLabel.getText());
		Assert.assertEquals("label", formLabel.getTagName());
		parameterSetupChrome();
		chromeDriver.get("http://localhost:8080/simpleMealPreferences/mealCategoryForm.html");
		formLabel=chromeDriver.findElement(By.id("meal-type-label"));
		Assert.assertEquals("Meal-type:", formLabel.getText());
		Assert.assertEquals("label", formLabel.getTagName());
	}
	
	@Test
	public void testMenuOptionOne() {
		try
		{
			WebElement menuList;
			WebElement menuItem;
			parameterSetupFireFox();
			fireFoxDriver.get("http://localhost:8080/simpleMealPreferences/mealCategoryForm.html");
			Actions builder=new Actions(fireFoxDriver);
			menuList=fireFoxDriver.findElement(By.id("meal-typeList"));
			String itemHeight=menuList.getAttribute("height");
			String itemWidth=menuList.getAttribute("width");
			System.out.println("Element is "+itemHeight +" in height");
			System.out.println("Element is "+itemWidth+" in width");
			int expectedHeight=31;
			int expectedWidth=101;
			//add one to the actual height to move to the list option below
			builder.moveToElement(menuList)
				.click()
				.moveByOffset(0, expectedHeight+1)
				.perform();
			Thread.sleep(SLEEP_TIME);
			menuItem=fireFoxDriver.findElement(By.id("meal-option-one"));
			Assert.assertEquals("breakfast",menuItem.getAttribute("value"));
			Assert.assertEquals("breakfast", menuItem.getText());
			builder.moveByOffset(expectedWidth+1, 0)
				.click()
				.perform();
		
			//Chrome test
			parameterSetupChrome();
			chromeDriver.get("http://localhost:8080/simpleMealPreferences/mealCategoryForm.html");
			builder=new Actions(chromeDriver);
			menuList=chromeDriver.findElement(By.id("meal-typeList"));
			itemHeight=menuList.getAttribute("height");
			itemWidth=menuList.getAttribute("width");
			System.out.println("Element is "+itemHeight +" in height");
			System.out.println("Element is "+itemWidth +" in width");
			//add one to the actual height to move to the list option below
			builder.moveToElement(menuList)
				.click()
				.moveByOffset(0, expectedHeight+1)
				.perform();
			Thread.sleep(SLEEP_TIME);
			menuItem=chromeDriver.findElement(By.id("meal-option-one"));
			Assert.assertEquals("breakfast",menuItem.getAttribute("value"));
			Assert.assertEquals("breakfast", menuItem.getText());
			builder.moveByOffset(expectedWidth+1, 0)
				.click()
				.perform();
		}
		catch(InterruptedException ie)
		{
			ie.printStackTrace();
		}
	}
	
	@Test
	public void testMenuOptionTwo() {
		try
		{
			WebElement menuList;
			WebElement menuItem;
			parameterSetupFireFox();
			fireFoxDriver.get("http://localhost:8080/simpleMealPreferences/mealCategoryForm.html");
			Actions builder=new Actions(fireFoxDriver);
			menuList=fireFoxDriver.findElement(By.id("meal-typeList"));
			String itemHeight=menuList.getAttribute("height");
			String itemWidth=menuList.getAttribute("width");
			System.out.println("Element is "+itemHeight +" in height");
			System.out.println("Element is "+itemWidth+" in width");
			int expectedHeight=31;
			int expectedWidth=101;
			//add one to the actual height times two to move to the list option below
			//second option
			builder.moveToElement(menuList)
				.click()
				.moveByOffset(0, expectedHeight+1)
				.perform();
			Thread.sleep(SLEEP_TIME);
			menuItem=fireFoxDriver.findElement(By.id("meal-option-two"));
			Assert.assertEquals("lunch",menuItem.getAttribute("value"));
			Assert.assertEquals("lunch", menuItem.getText());
			builder.moveByOffset((expectedWidth*2)+1, 0)
				.click()
				.perform();
		
			//Chrome test
			parameterSetupChrome();
			chromeDriver.get("http://localhost:8080/simpleMealPreferences/mealCategoryForm.html");
			builder=new Actions(chromeDriver);
			menuList=chromeDriver.findElement(By.id("meal-typeList"));
			itemHeight=menuList.getAttribute("height");
			itemWidth=menuList.getAttribute("width");
			System.out.println("Element is "+itemHeight +" in height");
			System.out.println("Element is "+itemWidth +" in width");
			//add one to the actual height times two to move to the list option below
			//second option
			builder.moveToElement(menuList)
				.click()
				.moveByOffset(0, expectedHeight+1)
				.perform();
			Thread.sleep(SLEEP_TIME);
			menuItem=chromeDriver.findElement(By.id("meal-option-two"));
			Assert.assertEquals("lunch",menuItem.getAttribute("value"));
			Assert.assertEquals("lunch", menuItem.getText());
			builder.moveByOffset((expectedWidth*2)+1, 0)
				.click()
				.perform();
		}
		catch(InterruptedException ie)
		{
			ie.printStackTrace();
		}
	}
	
	@Test
	public void testMenuOptionThree() {
		try
		{
			WebElement menuList;
			WebElement menuItem;
			parameterSetupFireFox();
			fireFoxDriver.get("http://localhost:8080/simpleMealPreferences/mealCategoryForm.html");
			Actions builder=new Actions(fireFoxDriver);
			menuList=fireFoxDriver.findElement(By.id("meal-typeList"));
			String itemHeight=menuList.getAttribute("height");
			String itemWidth=menuList.getAttribute("width");
			System.out.println("Element is "+itemHeight +" in height");
			System.out.println("Element is "+itemWidth+" in width");
			int expectedHeight=31;
			int expectedWidth=101;
			//add one to the actual height times two to move to the list option below
			//second option
			builder.moveToElement(menuList)
				.click()
				.moveByOffset(0, expectedHeight+1)
				.perform();
			Thread.sleep(SLEEP_TIME);
			menuItem=fireFoxDriver.findElement(By.id("meal-option-three"));
			Assert.assertEquals("dinner",menuItem.getAttribute("value"));
			Assert.assertEquals("dinner", menuItem.getText());
			builder.moveByOffset((expectedWidth*3)+1, 0)
				.click()
				.perform();
		
			//Chrome test
			parameterSetupChrome();
			chromeDriver.get("http://localhost:8080/simpleMealPreferences/mealCategoryForm.html");
			builder=new Actions(chromeDriver);
			menuList=chromeDriver.findElement(By.id("meal-typeList"));
			itemHeight=menuList.getAttribute("height");
			itemWidth=menuList.getAttribute("width");
			System.out.println("Element is "+itemHeight +" in height");
			System.out.println("Element is "+itemWidth +" in width");
			//add one to the actual height times two to move to the list option below
			//second option
			builder.moveToElement(menuList)
				.click()
				.moveByOffset(0, expectedHeight+1)
				.perform();
			Thread.sleep(SLEEP_TIME);
			menuItem=chromeDriver.findElement(By.id("meal-option-three"));
			Assert.assertEquals("dinner",menuItem.getAttribute("value"));
			Assert.assertEquals("dinner", menuItem.getText());
			builder.moveByOffset((expectedWidth*3)+1, 0)
				.click()
				.perform();
		}
		catch(InterruptedException ie)
		{
			ie.printStackTrace();
		}
	}
	
	@Test
	public void testMenuOptionFour() {
		try
		{
			WebElement menuList;
			WebElement menuItem;
			parameterSetupFireFox();
			fireFoxDriver.get("http://localhost:8080/simpleMealPreferences/mealCategoryForm.html");
			Actions builder=new Actions(fireFoxDriver);
			menuList=fireFoxDriver.findElement(By.id("meal-typeList"));
			String itemHeight=menuList.getAttribute("height");
			String itemWidth=menuList.getAttribute("width");
			System.out.println("Element is "+itemHeight +" in height");
			System.out.println("Element is "+itemWidth+" in width");
			int expectedHeight=31;
			int expectedWidth=101;
			//add one to the actual height times two to move to the list option below
			//second option
			builder.moveToElement(menuList)
				.click()
				.moveByOffset(0, expectedHeight+1)
				.perform();
			Thread.sleep(SLEEP_TIME);
			menuItem=fireFoxDriver.findElement(By.id("meal-option-four"));
			Assert.assertEquals("desert",menuItem.getAttribute("value"));
			Assert.assertEquals("desert", menuItem.getText());
			builder.moveByOffset((expectedWidth*4)+1, 0)
				.click()
				.perform();
		
			//Chrome test
			parameterSetupChrome();
			chromeDriver.get("http://localhost:8080/simpleMealPreferences/mealCategoryForm.html");
			builder=new Actions(chromeDriver);
			menuList=chromeDriver.findElement(By.id("meal-typeList"));
			itemHeight=menuList.getAttribute("height");
			itemWidth=menuList.getAttribute("width");
			System.out.println("Element is "+itemHeight +" in height");
			System.out.println("Element is "+itemWidth +" in width");
			//add one to the actual height times two to move to the list option below
			//second option
			builder.moveToElement(menuList)
				.click()
				.moveByOffset(0, expectedHeight+1)
				.perform();
			Thread.sleep(SLEEP_TIME);
			menuItem=chromeDriver.findElement(By.id("meal-option-four"));
			Assert.assertEquals("desert",menuItem.getAttribute("value"));
			Assert.assertEquals("desert", menuItem.getText());
			builder.moveByOffset((expectedWidth*4)+1, 0)
				.click()
				.perform();
		}
		catch(InterruptedException ie)
		{
			ie.printStackTrace();
		}
	}
	
	@Test
	public void testSearchFoodWithOptOne()
	{
		try
		{
			WebElement menuList;
			WebElement submitButton;
			WebElement selectionStatement;
			parameterSetupFireFox();
			fireFoxDriver.get("http://localhost:8080/simpleMealPreferences/mealCategoryForm.html");
			Actions builder=new Actions(fireFoxDriver);
			menuList=fireFoxDriver.findElement(By.id("meal-typeList"));
			String itemHeight=menuList.getAttribute("height");
			String itemWidth=menuList.getAttribute("width");
			System.out.println("Element is "+itemHeight +" in height");
			System.out.println("Element is "+itemWidth+" in width");
			int expectedHeight=31;
			int expectedWidth=101;
			//add one to the actual height times to move to the list option below
			//first option
			Select mealSelect=new Select(menuList);
			mealSelect.selectByVisibleText("breakfast");
			submitButton=fireFoxDriver.findElement(By.id("search-button"));
			builder.click(submitButton);
			builder.perform();
			Thread.sleep(SLEEP_TIME);
			selectionStatement=fireFoxDriver.findElement(By.id("selection-statement"));
			selectionStatement=fireFoxDriver.findElement(By.id("selection-statement"));
			Assert.assertEquals("Got meal category: breakfast", selectionStatement.getText());
			
	
			//Chrome test
			parameterSetupChrome();
			chromeDriver.get("http://localhost:8080/simpleMealPreferences/mealCategoryForm.html");
			builder=new Actions(chromeDriver);
			menuList=chromeDriver.findElement(By.id("meal-typeList"));
			itemHeight=menuList.getAttribute("height");
			itemWidth=menuList.getAttribute("width");
			System.out.println("Element is "+itemHeight +" in height");
			System.out.println("Element is "+itemWidth +" in width");
			//add one to the actual height times to move to the list option below
			//first option
			mealSelect=new Select(menuList);
			mealSelect.selectByVisibleText("breakfast");
			submitButton=chromeDriver.findElement(By.id("search-button"));
			builder.click(submitButton);
			builder.perform();
			Thread.sleep(SLEEP_TIME);
			selectionStatement=fireFoxDriver.findElement(By.id("selection-statement"));
			selectionStatement=chromeDriver.findElement(By.id("selection-statement"));
			Assert.assertEquals("Got meal category: breakfast", selectionStatement.getText());
		}
		catch(InterruptedException ie)
		{
			ie.printStackTrace();
		}
	}
	
	@Test
	public void testSearchFoodWithOptTwo()
	{
		try{
				WebElement menuList;
				WebElement submitButton;
				WebElement selectionStatement;
				parameterSetupFireFox();
				fireFoxDriver.get("http://localhost:8080/simpleMealPreferences/mealCategoryForm.html");
				Actions builder=new Actions(fireFoxDriver);
				menuList=fireFoxDriver.findElement(By.id("meal-typeList"));
				String itemHeight=menuList.getAttribute("height");
				String itemWidth=menuList.getAttribute("width");
				System.out.println("Element is "+itemHeight +" in height");
				System.out.println("Element is "+itemWidth+" in width");
				int expectedHeight=31;
				int expectedWidth=101;
				//add one to the actual height times two to move to the list option below
				//second option
				Select mealSelect=new Select(menuList);
				mealSelect.selectByVisibleText("lunch");
				submitButton=fireFoxDriver.findElement(By.id("search-button"));
				builder.click(submitButton);
				builder.perform();
				Thread.sleep(SLEEP_TIME);
				selectionStatement=fireFoxDriver.findElement(By.id("selection-statement"));
				Assert.assertEquals("Got meal category: lunch", selectionStatement.getText());
			
	
				//Chrome test
				parameterSetupChrome();
				chromeDriver.get("http://localhost:8080/simpleMealPreferences/mealCategoryForm.html");
				builder=new Actions(chromeDriver);
				menuList=chromeDriver.findElement(By.id("meal-typeList"));
				itemHeight=menuList.getAttribute("height");
				itemWidth=menuList.getAttribute("width");
				System.out.println("Element is "+itemHeight +" in height");
				System.out.println("Element is "+itemWidth +" in width");
				//add one to the actual height times two to move to the list option below
				//second option
			
				mealSelect=new Select(menuList);
				mealSelect.selectByVisibleText("lunch");
				submitButton=chromeDriver.findElement(By.id("search-button"));
				builder.click(submitButton);
				builder.perform();
				Thread.sleep(SLEEP_TIME);
				selectionStatement=chromeDriver.findElement(By.id("selection-statement"));
				Assert.assertEquals("Got meal category: lunch", selectionStatement.getText());
		}
		catch(InterruptedException ie)
		{
			ie.printStackTrace();		
		}
			
	}
	
	@Test
	public void testSearchFoodWithOptThree()
	{
		try
		{
			WebElement menuList;
			WebElement menuItem;
			WebElement submitButton;
			WebElement selectionStatement;
			parameterSetupFireFox();
			fireFoxDriver.get("http://localhost:8080/simpleMealPreferences/mealCategoryForm.html");
			Actions builder=new Actions(fireFoxDriver);
			menuList=fireFoxDriver.findElement(By.id("meal-typeList"));
			String itemHeight=menuList.getAttribute("height");
			String itemWidth=menuList.getAttribute("width");
			System.out.println("Element is "+itemHeight +" in height");
			System.out.println("Element is "+itemWidth+" in width");
			int expectedHeight=31;
			int expectedWidth=101;
			//add one to the actual height times three to move to the list option below
			//third option
			Select mealSelect=new Select(menuList);
			mealSelect.selectByVisibleText("dinner");
			submitButton=fireFoxDriver.findElement(By.id("search-button"));
			builder.click(submitButton);
			builder.perform();
			Thread.sleep(SLEEP_TIME);
			selectionStatement=fireFoxDriver.findElement(By.id("selection-statement"));
			Assert.assertEquals("Got meal category: dinner", selectionStatement.getText());
	
			//Chrome test
			parameterSetupChrome();
			chromeDriver.get("http://localhost:8080/simpleMealPreferences/mealCategoryForm.html");
			builder=new Actions(chromeDriver);
			menuList=chromeDriver.findElement(By.id("meal-typeList"));
			itemHeight=menuList.getAttribute("height");
			itemWidth=menuList.getAttribute("width");
			System.out.println("Element is "+itemHeight +" in height");
			System.out.println("Element is "+itemWidth +" in width");
			//add one to the actual height times three to move to the list option below
			//third option
			mealSelect=new Select(menuList);
			mealSelect.selectByVisibleText("dinner");
			submitButton=chromeDriver.findElement(By.id("search-button"));
			builder.click(submitButton);
			builder.perform();
			Thread.sleep(SLEEP_TIME);
			selectionStatement=chromeDriver.findElement(By.id("selection-statement"));
			Assert.assertEquals("Got meal category: dinner", selectionStatement.getText());
		}
		catch(InterruptedException ie)
		{
			ie.printStackTrace();
		}
		
	}
	
	@Test
	public void testSearchFoodWithOptFour()
	{
		try
		{
			WebElement menuList;
			WebElement menuItem;
			WebElement submitButton;
			WebElement selectionStatement;
			parameterSetupFireFox();
			fireFoxDriver.get("http://localhost:8080/simpleMealPreferences/mealCategoryForm.html");
			Actions builder=new Actions(fireFoxDriver);
			menuList=fireFoxDriver.findElement(By.id("meal-typeList"));
			String itemHeight=menuList.getAttribute("height");
			String itemWidth=menuList.getAttribute("width");
			System.out.println("Element is "+itemHeight +" in height");
			System.out.println("Element is "+itemWidth+" in width");
			int expectedHeight=31;
			int expectedWidth=101;
			//add one to the actual height times four to move to the list option below
			//fourth option
			Select mealSelect=new Select(menuList);
			mealSelect.selectByVisibleText("desert");
			submitButton=fireFoxDriver.findElement(By.id("search-button"));
			builder.click(submitButton);
			builder.perform();
			Thread.sleep(SLEEP_TIME);
			selectionStatement=fireFoxDriver.findElement(By.id("selection-statement"));
			Assert.assertEquals("Got meal category: desert", selectionStatement.getText());
	
			//Chrome test
			parameterSetupChrome();
			chromeDriver.get("http://localhost:8080/simpleMealPreferences/mealCategoryForm.html");
			builder=new Actions(chromeDriver);
			menuList=chromeDriver.findElement(By.id("meal-typeList"));
			itemHeight=menuList.getAttribute("height");
			itemWidth=menuList.getAttribute("width");
			System.out.println("Element is "+itemHeight +" in height");
			System.out.println("Element is "+itemWidth +" in width");
			//add one to the actual height times two to move to the list option below
			//second option
			mealSelect=new Select(menuList);
			mealSelect.selectByVisibleText("desert");
			submitButton=chromeDriver.findElement(By.id("search-button"));
			builder.click(submitButton);
			builder.perform();
			Thread.sleep(SLEEP_TIME);
			selectionStatement=chromeDriver.findElement(By.id("selection-statement"));
			Assert.assertEquals("Got meal category: desert", selectionStatement.getText());		
		}
		catch(InterruptedException ie)
		{
			ie.printStackTrace();
		}
	}
	
	/**
	 * Sets the location of the fireFox web driver  and maximizes the windows and sets browser timeouts
	 */
	private void parameterSetupFireFox()
	{
		System.setProperty("webdriver.gecko.driver", "/home/ryan/dev/webDrivers/geckodriver");
		this.fireFoxDriver=new FirefoxDriver();
		this.fireFoxDriver.manage().window().maximize();
		this.fireFoxDriver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);
		this.fireFoxDriver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
	}
	
	private void parameterSetupChrome()
	{
		System.setProperty("webdriver.chrome.driver", "/home/ryan/dev/webDrivers/chromedriver");
		this.chromeDriver=new ChromeDriver();
		this.chromeDriver.manage().window().maximize();
		this.chromeDriver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);
		this.chromeDriver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
	}
	
}
