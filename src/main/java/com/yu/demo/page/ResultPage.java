package com.yu.demo.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ResultPage extends PageObject {
	
	public static By resultText = By.className("pagination-bar-results"); 
	public static By noneResult = By.xpath("//div[@class='search-empty']/div");
	
	public static By resultItems = By.xpath("//ul[@class='product__listing product__grid']/div");
	public static By itemLink = By.xpath("//ul[@class='product__listing product__grid']/div/a"); //attribue href
	public static By itemImage = By.xpath("//ul[@class='product__listing product__grid']/div/a/img"); //ul[@class='product__listing product__grid']/div[1]/a/img
	public static By itemTitle = By.xpath("//ul[@class='product__listing product__grid']/div/div[@class='details']/a"); //ul[@class='product__listing product__grid']/div/div[@class='details']/a
	public static By itemPrice = By.xpath("//ul[@class='product__listing product__grid']/div/div[@class='details']/div[@class='price']"); //ul[@class='product__listing product__grid']/div/div[@class='details']/div[@class='price']
	public static By itemAction = By.xpath("//ul[@class='product__listing product__grid']/div//div[@class='SearchResultsGrid-ListAddToCartAction']"); //ul[@class='product__listing product__grid']/div//div[@class='SearchResultsGrid-ListAddToCartAction']
	
	
	public ResultPage(WebDriver driver) {
		super(driver);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
