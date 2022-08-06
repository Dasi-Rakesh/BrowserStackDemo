package com.flipkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import browser.extensions.Wait;

public class HomePage extends BasePage{	

	public HomePage(WebDriver driver) {
		super(driver);		
	}

	private By CloseButton = By.xpath("(//button)[2]");

	private By SearchBar = By.xpath("//input[@name = 'q']");

	private By SearchIcon = By.xpath("//button[@type='submit']");

	public By Categories(String category) {
		return By.xpath("//span[text()='CATEGORIES']/following::a[@title='" + category + "']");
	}

	public By BrandFilter(String filter) {
		return By.xpath("//input[@type='checkbox']/following::div[text()='" + filter + "']");
	}
	
	public void ClosePopUp() {
		driver.findElement(CloseButton).click();
        System.out.println("Element closed");
    }

    public void SearchForProduct(String searchItem) throws InterruptedException{
    	Thread.sleep(3000);
        WebElement element = driver.findElement(SearchBar);
        String input = "Samsung";
        for(int i =0; i<input.length(); i++) {
        	element.sendKeys(input.substring(i, i+1));
        	Thread.sleep(500);
        }
        
        driver.findElement(SearchIcon).click();
       
    }

    public void SelectCategory(String category){
        Wait.shortSleep();
        Assert.assertTrue(Wait.IsElementDisplayed(Categories(category)));
        Wait.waitForElementVisibleAndClick(Categories(category));
    }

    public void SelectBrandFilter(String filter){
        Assert.assertTrue(Wait.IsElementDisplayed(BrandFilter(filter)));
        Wait.waitForElementVisibleAndClick(BrandFilter(filter));
    }

}
