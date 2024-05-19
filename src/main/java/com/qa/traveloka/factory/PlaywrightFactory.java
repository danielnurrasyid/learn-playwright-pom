package com.qa.traveloka.factory;

import com.microsoft.playwright.*;

public class PlaywrightFactory {
	
	Page page;
	Playwright playwright;
	Browser browser;
	BrowserContext browserContext;
	
	public Page initBrowser(String browserName) {
		
		System.out.println("You are using browser " + browserName); //debugging purpose
		
		playwright = Playwright.create();
		
		switch(browserName) {
			case "chromium":
				browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
			case "firefox":
				browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
			case "safari":
				browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
			default:
				System.out.println("You need to define a right browser!");
			break;
		}
		
		browserContext = browser.newContext();
		
		page = browserContext.newPage();
		
		page.navigate("https://www.traveloka.com/");
		
		return page;
	}


}
