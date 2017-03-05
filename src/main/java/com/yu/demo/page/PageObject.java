package com.yu.demo.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageObject {
	protected WebDriver driver;
	
	public PageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement element(By locator) {
		return driver.findElement(locator);
	}
	
	public List<WebElement> elements(By locator) {
		return driver.findElements(locator);
	}
	
	public boolean doesElementExist(By locator) {  
        boolean flag = false;  
        try {  
            WebElement element=driver.findElement(locator);  
            flag=(null!=element);  
        } catch (NoSuchElementException e) {  
            System.out.println(String.format("Element: %s does not exist!",locator.toString())); 
        }  
        return flag;  
    }  

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
