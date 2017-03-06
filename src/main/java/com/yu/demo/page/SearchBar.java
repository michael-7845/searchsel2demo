package com.yu.demo.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * page object for search bar.
 * An example for page object layer.
 */
public class SearchBar extends PageObject{
	
	// web elements initilization
	public static By input = By.id("js-site-search-input"); 
	public static By find = By.xpath("//*[@class='btn btn-link js_search_button']"); //By.className("btn.btn-link.js_search_button");
	
	public SearchBar(WebDriver driver) {
		super(driver);
	}
	
	public static void demo() {
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		SearchBar s = new SearchBar(new FirefoxDriver(capability));
		s.driver.get("https://localhost:9002/yacceleratorstorefront/?site=apparel-uk");
	}

	public static void main(String[] args) {
		demo();
	}

}
