package com.qa.traveloka.tests;

import org.testng.Assert;
import org.testng.annotations.*;

import com.microsoft.playwright.*;
import com.qa.traveloka.factory.PlaywrightFactory;
import com.qa.traveloka.pages.TravelokaHomePage;

public class LoginRegression {

	Page page;
	PlaywrightFactory pf;
	TravelokaHomePage travelokaHomePage;
	
	
	@BeforeTest
	public void setup() {
		pf = new PlaywrightFactory();
		page = pf.initBrowser("chromium");
		
		travelokaHomePage = new TravelokaHomePage(page);
	}
	
	@Test
	public void openLoginForm() {
		travelokaHomePage.clickLoginButton();
		String actualFormInformation = travelokaHomePage.getRegisterFormInformation();
		Assert.assertEquals(actualFormInformation, "Log In/Register");
	}
	
	@AfterTest
	public void tearDown() {
		page.context().browser().close();
		
	}
	
}
