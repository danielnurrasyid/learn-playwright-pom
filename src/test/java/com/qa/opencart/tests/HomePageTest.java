package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class HomePageTest extends BaseTest { //EXTENDS inheritence from basetest as a parent class to homepagetest as a child class
	//this is a place to create a testcase
	
	@Test
	public void homePageTitleTest() {
		String actualTitle = homePage.getHomePageTitle();
		// do assertion (actualTitle, Expected)
		Assert.assertEquals(actualTitle, AppConstants.HOME_PAGE_TITLE);
	}
	
	@Test
	public void homePageURLTest() {
		String actualURL = homePage.getHomePageURL();
		Assert.assertEquals(actualURL, prop.getProperty("url"));
		
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
	
	

	
	
	
	
}
