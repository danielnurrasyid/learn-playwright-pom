package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest {
	
	@Test(priority = 1)
	public void loginPageNavigationTest() {
		//Page Chaining 
		//HomePage to a LoginPage
		//Then we store it in loginPage that inherit from BaseTest
		loginPage = homePage.navigateToLoginPage(); // the function is returning a page
		String actualLoginPageTitle = loginPage.getLoginPageTitle();
		System.out.println("Page Actual Title: " + actualLoginPageTitle);
		Assert.assertEquals(actualLoginPageTitle, AppConstants.LOGIN_PAGE_TITLE);
		
	}

	//priority is used to create a sequence of test case
	
	@Test(priority = 2)
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Test(priority = 3)
	public void appLoginTest() {
		Assert.assertTrue(loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim()));
		
	}
	
}
