package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

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
	
	Properties prop;

	/*
	 * ThreadLocal
	 * is a Java Feature that we can use as a best practice in parallel testing.
	 * ThreadLocal is able to copy Page to each Thread
	 * */
	
	private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<Browser>();
	private static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<BrowserContext>();
	private static ThreadLocal<Page> tlPage = new ThreadLocal<Page>();
	private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<Playwright>();
	
	// we need to use getter setter to use a ThreadLocal
	public static Browser getBrowser() {
		return tlBrowser.get();
	}
	public static BrowserContext getBrowserContext() {
		return tlBrowserContext.get();
	}
	public static Page getPage() {
		return tlPage.get();
	}
	public static Playwright getPlaywright() {
		return tlPlaywright.get();
	}
	
	//Open Browser
	//instead of public void we must change to public Page, because we returning a Page
	public Page initBrowser(Properties prop) {
		
		//this is getting a properties from config file.
		String browserName = prop.getProperty("browser");
		
		System.out.println("Browser name is : " + browserName);
		
		//playwright = Playwright.create();
		tlPlaywright.set(Playwright.create());
		
		switch(browserName.toLowerCase()) {
		case "chromium":
			//browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		case "firefox":
			//browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
			tlBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		case "safari":
			//browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
			tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		case "chrome":
			//need setchannel to set the browsername that is chrome.
			//browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
			tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false)));
			break;
		default:
			//if there is no browser
			System.out.println("Please pass the right browser name...");
			break;
		}
		
		//create new context of browser
		//browserContext = browser.newContext();
		tlBrowserContext.set(getBrowser().newContext());
		
		//create new page
		//page = browserContext.newPage();
		tlPage.set(getBrowserContext().newPage());
		
		//navigate to url
		//page.navigate(prop.getProperty("url"));
		getPage().navigate(prop.getProperty("url").trim());
		
		
		//return page;
		return getPage();
		
	}
	
	
	/*
	 * this method is used to Initialize properties from config file
	 * Connecting a config file and load the file (using Properties Class) and then
	 * resulting key and value format
	 * */
	
	public Properties init_prop() {
		try {
			FileInputStream ip = new FileInputStream("./src/test/resource/config/config.properties"); //. is representing a project directory outside of src file
			prop = new Properties(); // Properties class is used to load the FileInputStream Object
			prop.load(ip);//this line is used to load from file and connect the config file resulting key value format.
		
		} catch (FileNotFoundException e) { // this exception used,if there is no file
			e.printStackTrace();
		} catch(IOException e) { // this exception used, if when load the file there is an error
			e.printStackTrace();
		}
		
		return prop;
		
	}
	
	
	
	
}
