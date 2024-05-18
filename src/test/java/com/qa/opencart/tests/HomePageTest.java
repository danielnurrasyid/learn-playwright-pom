package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.microsoft.playwright.*;
import com.qa.opencart.factory.PlaywrightFactory;
import com.qa.opencart.pages.HomePage;

public class HomePageTest {
	//this is a place to create a testcase
	
	Page page;
	//class that we already created
	PlaywrightFactory pf;
	HomePage homePage;
	
	@BeforeTest
	public void setup() {
		//call the initBrowser from playwrightfactory
		pf = new PlaywrightFactory(); //creating object
		page = pf.initBrowser("chromium");
		
		//in this creating object we need to pass a page, because of the constructor
		//because in the homepage class we use page in methods
		homePage = new HomePage(page);
				
	}
	
	@Test
	public void homePageTitleTest() {
		String actualTitle = homePage.getHomePageTitle();
		// do assertion (actualTitle, Expected)
		Assert.assertEquals(actualTitle, "Your Store");
	}
	
	@Test
	public void homePageURLTest() {
		String actualURL = homePage.getHomePageURL();
		Assert.assertEquals(actualURL, "https://naveenautomationlabs.com/opencart/");
		
	}
	
	//this is a normal or just a basic data we use
//	@Test
//	public void searchTest() {
//		String actualSearchResult = homePage.doSearch("Macbook");
//		Assert.assertEquals(actualSearchResult, "Search - Macbook");
//	}
	
	// BELOW is the bonus
	
	// data provider is provide by testng to create a test data.
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"Macbook"},
			{"iMac"},
			{"Samsung"}
		};
	}
	
	@Test(dataProvider = "getProductData")
	public void searchTest(String productName) {
		String actualSearchResult = homePage.doSearch(productName);
		Assert.assertEquals(actualSearchResult, "Search - " + productName);
	}
	
	
	@AfterTest
	public void tearDown() {
		//closing browser because we use browsercontext so we need .context()
		page.context().browser().close();
	}
	
	
	
	
}
