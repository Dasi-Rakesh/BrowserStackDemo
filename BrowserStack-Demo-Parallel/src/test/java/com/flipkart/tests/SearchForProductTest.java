package com.flipkart.tests;

import org.testng.annotations.Test;

import com.flipkart.pages.HomePage;
import com.flipkart.pages.SearchPage;

public class SearchForProductTest extends BaseTest {

	@Test
	public void GetProductNameAndPriceList() throws InterruptedException {
		System.out.println("Naviagted to URL");
		HomePage homePage = new HomePage(driver);
		SearchPage searchPage = new SearchPage(driver);
		//Close the Login Popup
		homePage.ClosePopUp();
		//Search for product
		homePage.SearchForProduct("Samsung Galaxy S10");
		//Select Category
		homePage.SelectCategory("Mobiles");
		//Select Brand
		homePage.SelectBrandFilter("SAMSUNG");
		searchPage.SelectFlipkartAssuredCheckbox();
		//Sort Price from High to Low
		searchPage.SelectPriceHighToLow();
		//Get All results.
		searchPage.GetProductResults();
	}

}
