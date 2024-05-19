package com.qa.traveloka.pages;

import com.microsoft.playwright.*;

public class TravelokaHomePage {
	
	Page page;
	//1. Define Constructor, initialize an object of page that we need for other function in this class.
	public TravelokaHomePage(Page page) {
		this.page = page;
	}
	
	//2. Define the locators
	private String loginButton = "//div[contains(text(),'Log In')]";
	private String loginFormInformation = "//h1[contains(text(),'Register')]";
	
	
	
	//3. Define an actions method
	public void clickLoginButton() {
		page.click(loginButton);
	}
	
	public String getRegisterFormInformation() {
		String information = page.textContent(loginFormInformation);
		System.out.println("Information is: " + information);
		return information;
		
	}

}
