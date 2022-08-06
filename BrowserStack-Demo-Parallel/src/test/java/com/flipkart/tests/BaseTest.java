package com.flipkart.tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	private static final String USERNAME = "rakeshdasi_kBWiKz";
	private static final String ACCESS_KEY = "FBs74E37YyD4M43fD2Lr";
	private static final String BROWSERSTACK_URL = "https://" + USERNAME + ":" + ACCESS_KEY
			+ "@hub-cloud.browserstack.com/wd/hub";
	
	WebDriver driver;
	
	@Parameters("browserName")
	@BeforeTest
	public void setup(String browserName) throws MalformedURLException {
		System.out.println("Browser Name is : " + browserName);

		if (browserName.equalsIgnoreCase("Chrome")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("os", "Windows");
			capabilities.setCapability("os_version", "10");
			capabilities.setCapability("browser", "Chrome");
			capabilities.setCapability("browser_version", "80");
			capabilities.setCapability("name", "Test in Chrome");
			driver = new RemoteWebDriver(new URL(BROWSERSTACK_URL), capabilities);

		} else if (browserName.equalsIgnoreCase("Firefox")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("os", "Windows");
			capabilities.setCapability("os_version", "10");
			capabilities.setCapability("browser", "Firefox");
			capabilities.setCapability("browser_version", "latest");
			capabilities.setCapability("name", "Test in Firefox");
			driver = new RemoteWebDriver(new URL(BROWSERSTACK_URL), capabilities);

		} else if (browserName.equalsIgnoreCase("Safari")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("os", "OS X");
			capabilities.setCapability("os_version", "Big Sur");
			capabilities.setCapability("browser", "Safari");
			capabilities.setCapability("browser_version", "latest");
			capabilities.setCapability("name", "Test in Safari");
			driver = new RemoteWebDriver(new URL(BROWSERSTACK_URL), capabilities);
		} else if(browserName.equalsIgnoreCase("Local")) {
			 WebDriverManager.firefoxdriver().setup();
             driver = new FirefoxDriver();
		}
		
	}
	
	@BeforeMethod
	public void launchApplication() {		
		driver.get("https://www.flipkart.com");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		String title = driver.getTitle();
		System.out.println("Website Title is : " + title);
		Assert.assertEquals("Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!", title);
		System.out.println("Page Title is verified");
		
	}
	
	@AfterTest
	public void teardown() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
		System.out.println("Test Completed");
	}

}
