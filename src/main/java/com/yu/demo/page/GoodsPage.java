package com.yu.demo.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * page object for goods details.
 * An example for page object layer.
 */
public class GoodsPage extends PageObject {
	
	public static By code = By.xpath("//div[@class='name']/span[@class='code']");

	public GoodsPage(WebDriver driver) {
		super(driver);
	}

}
