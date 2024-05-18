package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class HomePage {
	
	private Page page;
	
	//1. String Locators (Object Repository) 
	//Using csslocator
	private String searchTextField = "input[name='search']";
	private String searchIcon = "div#search button";
	private String searchPageHeader = "div#content h1";
	
	
	//2. page Constructor
	//is to pass a Page from an object of the HomePage that we need to create later 
	public HomePage(Page page) {
		this.page = page;
	}
	
	//3. Page Actions/method
	public String getHomePageTitle() {
		String title = page.title();
		System.out.println("Page title: " + title); // for debugging process
		return title;
	}
	
	public String getHomePageURL() {
		String url = page.url();
		System.out.println("Page url: " + url); // for debugging process
		return url;
	}
	
	public String doSearch(String productName) {
		page.fill(searchTextField, productName);
		page.click(searchIcon);
		
		String header = page.textContent(searchPageHeader);
		System.out.println("Search header: " + header); // for debugging process
		
		//returning Search - productName
		return header;
		
	}
	
	/*
	 * With all the variables is private
	 * we basically create a Encapsulation Concept in this Class
	 * 
	 * NEVER write an assertion in this java class. it's a BAD PRACTICE according to Page Object Model
	 * Assertion is a responsible of a testng class.
	 * 
	 * halo
	 * */
	
	

}
