package com.yu.demo.service;

import org.openqa.selenium.WebDriver;

import com.yu.demo.page.SearchBar;

/**
 * An example for the service layer
 */
public class SearchAction {
	
	private WebDriver driver;
	private SearchBar search;
	
	public SearchAction(WebDriver driver) {
		this.driver = driver;
		this.search = new SearchBar(driver);
	}

	public void submit(String key) {
		search.element(SearchBar.input).sendKeys(key);
		search.element(SearchBar.input).submit();
	}
	
	public void click(String key) {
		search.element(SearchBar.input).sendKeys(key);
		search.element(SearchBar.find).click();
	}
}
