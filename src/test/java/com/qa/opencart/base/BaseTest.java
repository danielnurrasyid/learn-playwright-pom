package com.qa.opencart.base;

import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.microsoft.playwright.Page;
import com.qa.opencart.factory.PlaywrightFactory;
import com.qa.opencart.pages.HomePage;

public class BaseTest {
	
	//this class is define before and after test
	
	Page page;
	//class that we already created
	PlaywrightFactory pf;
	protected HomePage homePage;
	
	protected Properties prop;
	
	@BeforeTest
	public void setup() {
		//call the initBrowser from playwrightfactory
		pf = new PlaywrightFactory(); //creating object
		
		//call initprop to get a config data that we want to use. such as a browser.
		prop = pf.init_prop();
		
		//this parameter is using prop, because the data of browserName is in the config file
		page = pf.initBrowser(prop);
		
		//in this creating object we need to pass a page, because of the constructor
		//because in the homepage class we use page in methods
		homePage = new HomePage(page);
		
		System.out.println("Setup Before Test Success!");
				
	}
	
	
	@AfterTest
	public void tearDown() {
		//closing browser because we use browsercontext so we need .context()
		page.context().browser().close();

		System.out.println("After Test Success!");
	}
	
}
