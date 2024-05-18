package com.qa.opencart.factory;

import com.microsoft.playwright.*;


/*
 * POM or we can say Page Object Model
 * one of the structure of this design pattern
 * every page should create a java class
 * */
public class PlaywrightFactory {
	
	//initializing
	Playwright playwright;
	Browser browser;
	BrowserContext browserContext;
	Page page;
	
	//Open Browser
	
	//intead of public void we must change to public Page, because we returning a Page
	public Page initBrowser(String browserName) {
		
		System.out.println("Browser name is : " + browserName);
		playwright = Playwright.create();
		
		switch(browserName.toLowerCase()) {
		case "chromium":
			browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
		case "firefox":
			browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
		case "safari":
			browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
		case "chrome":
			//need setchannel to set the browsername that is chrome.
			browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
			break;
		default:
			//if there is no browser
			System.out.println("Please pass the right browser name...");
			break;
		}
		
		//create new context of browser
		browserContext = browser.newContext();
		
		//create new page
		page = browserContext.newPage();
		
		//navigate to url
		page.navigate("https://naveenautomationlabs.com/opencart/");
		
		
		return page;
		
		
		
	}

}
