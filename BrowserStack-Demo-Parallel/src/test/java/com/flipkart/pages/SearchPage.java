package com.flipkart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import browser.extensions.Wait;

public class SearchPage extends BasePage {

	public SearchPage(WebDriver driver) {
		super(driver);		
	}

	private By FilpKartAssuredCheckbox = By.xpath("(//input[@type='checkbox']/following::div/img[contains(@src,'flixcart')])[1]");

    private By PriceHighToLow = By.xpath("//div[text()='Price -- High to Low']");

    private By ProductResults = By.xpath("//div[@class='_4rR01T']");

    private By ProductPrices = By.xpath("//div[@class='_30jeq3 _1_WHN1']");

    public By Categories(String category){
        return By.xpath("//span[text()='CATEGORIES']/following::a[@title='"+category+"']");
    }

    public By BrandFilter(String filter){
        return By.xpath("//input[@type='checkbox']/following::div[text()='"+filter+"']");
    }
    
    public void SelectFlipkartAssuredCheckbox() {
        Wait.shortSleep();
        Assert.assertTrue(Wait.IsElementDisplayed(FilpKartAssuredCheckbox));
        Wait.waitForElementVisibleAndClick(FilpKartAssuredCheckbox);
    }

    public void SelectPriceHighToLow() {
        Wait.shortSleep();
        Assert.assertTrue(Wait.IsElementDisplayed(PriceHighToLow));
        Wait.waitForElementVisibleAndClick(PriceHighToLow);
    }

    public void GetProductResults() {
        Wait.shortSleep();
        List<WebElement> products = Wait.getElements(ProductResults);
        List<WebElement> productPrices = Wait.getElements(ProductPrices);
        for (int i = 0; i < products.size(); i++) {
            WebElement productName = products.get(i);
            WebElement productPrice = productPrices.get(i);
            System.out.println("Product Name  --> |" + productName.getText() + "| Product Price --> |" + productPrice.getText() + "| ");            
        }
    }
}
