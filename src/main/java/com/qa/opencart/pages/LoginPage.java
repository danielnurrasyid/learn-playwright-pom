package com.qa.opencart.pages;

import java.net.Socket;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;

public class LoginPage {
	
	private Page page;
	
	//1. Define Locators
	//using xpath selector
	private String emailId = "//input[@placeholder='E-Mail Address']";
	private String password = "//input[@id='input-password']";
	private String loginButton = "//input[@type='submit']";
	private String forgotPwdLink = "//a[text()='Forgotten Password']/parent::div[@class='form-group']";
	private String logoutLink = "//a[@class='list-group-item' and text()='Logout']";
	
	//2. Define Constructor
	public LoginPage(Page page) {
		this.page = page;
	}
	
	//3. Page Actions
	public String getLoginPageTitle() {
		return page.title();
	}
	
	public boolean isForgotPwdLinkExist() {
		//we using isVisible to check if forgotPwdLink is there or not?
		return page.isVisible(forgotPwdLink);
	}
	
	public boolean doLogin(String appUsername, String appPassword) {
		System.out.println("App creds: " + appUsername + ":" + appPassword);
		page.fill(emailId, appUsername);
		page.fill(password, appPassword);
		page.click(loginButton);
		
		if(page.isVisible(logoutLink)) {
			System.out.println("User is logged in successfully...");
			return true;
		}
		
		return false;
	}

}
