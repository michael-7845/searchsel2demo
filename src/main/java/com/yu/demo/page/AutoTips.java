package com.yu.demo.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AutoTips extends PageObject {

	public static By autoList = By.id("ui-id-1");
	public static By autoItems = By.className("ui-menu-item");
	public static By links = By.xpath("//ul[@id='ui-id-1']/li/a");
	
	public AutoTips(WebDriver driver) {
		super(driver);
	}

}
