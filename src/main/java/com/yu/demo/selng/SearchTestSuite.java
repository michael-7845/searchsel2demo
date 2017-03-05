package com.yu.demo.selng;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.List;

import com.yu.demo.page.AutoTips;
import com.yu.demo.page.GoodsPage;
import com.yu.demo.page.SearchBar;
import com.yu.demo.page.ResultPage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static com.yu.demo.service.Utility.*;

public class SearchTestSuite {
	
	WebDriver driver;
	SearchBar search;
	ResultPage result;
	AutoTips auto;
	GoodsPage goods;
	
	@BeforeClass
	public void beforeClass() {
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		driver=new FirefoxDriver(capability);
		search = new SearchBar(driver);
		result = new ResultPage(driver);
		auto = new AutoTips(driver);
		goods = new GoodsPage(driver);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://localhost:9002/yacceleratorstorefront/?site=apparel-uk");
	}

	@AfterMethod
	public void afterMethod() {
	    
	}
	
	//TC01 使用默认搜索条件搜索
	@Test(enabled=true)
	public void testCase01() {
		assertEquals("true", search.element(SearchBar.find).getAttribute("disabled"));
		search.element(SearchBar.input).sendKeys("\n");
		thinkTime(3000);
		assertFalse(result.doesElementExist(ResultPage.resultText));
		assertFalse(result.doesElementExist(ResultPage.noneResult));
	}
	
	//TC02 使用关键字成功搜索出内容
	@Test(enabled=true)
	public void testCase02_1() {
		search.element(SearchBar.input).sendKeys("a b");
		search.element(SearchBar.input).submit();
		thinkTime(3000);
		assertTrue(result.element(ResultPage.resultText).getText().endsWith("Products found"));
		
		search.element(SearchBar.input).sendKeys("a c");
		search.element(SearchBar.find).click();
		thinkTime(3000);
		assertTrue(result.element(ResultPage.resultText).getText().endsWith("Products found"));
		
		search.element(SearchBar.input).sendKeys("a b");
		thinkTime(1000);
		auto.elements(AutoTips.autoItems).get(0).click();
		thinkTime(3000);
		assertTrue(result.element(ResultPage.resultText).getText().endsWith("Products found"));
	}
	
	//如果搜索商品，跳转商详页
	@Test(enabled=true)
	public void testCase02_2() {
		search.element(SearchBar.input).sendKeys("a b");
		thinkTime(1000);
		String link = auto.elements(AutoTips.links).get(5).getAttribute("href");
		String skuId = link.split("/")[link.split("/").length-1];
		
		auto.elements(AutoTips.autoItems).get(5).click();
		thinkTime(3000);
		assertEquals(skuId, goods.element(GoodsPage.code).getText());
	}
	
	//TC03 搜索结果正确可用
	@Test(enabled=true)
	public void testCase03() {
		search.element(SearchBar.input).sendKeys("mandarine");
		search.element(SearchBar.input).submit();
		thinkTime(3000);
		
		//记录条数正确
		assertEquals(4, result.elements(ResultPage.resultItems).size());
		//单项内容正确
		assertTrue(result.doesElementExist(ResultPage.itemImage));
		assertTrue(result.doesElementExist(ResultPage.itemTitle));
		assertTrue(result.doesElementExist(ResultPage.itemPrice));
		assertTrue(result.doesElementExist(ResultPage.itemAction));
		
		//排序方式正确
		List<WebElement> links = result.elements(result.itemLink);
		String[] expectedSkuIds = {"300611800", "300611801", "300611802", "300611803"};
		for(int i=0; i<4; i++) {
			String link = links.get(i).getAttribute("href");
			String skuId = link.split("/")[link.split("/").length-1];
			assertEquals(expectedSkuIds[i], skuId);
		}
	}
	
	//TC04 使用特殊内容搜索不会产生异常
    @DataProvider(name="keys04")
    public Object[][] Keys04(){
        return new Object[][]{
                {"cucucu欧迈噶"},
                {"包￥"},
                {"A＠"},
                {"aBcDeFgZ"},
                {"[]{}\'\":%=&+?#~`!@$^*(),._-/\\|<>"},
                {"1234567890"},
                {"\n\b\t\f"},
                {"NULL"},
                {"Null"},
                {"<br><tr><td>"},
                {"alert('1');"},
                {" ab"},
                {"a b"},
                {"ab "},
                {" ab "},
        };
    }
    
    @Test(dataProvider="keys04", enabled=true)
    public void testCase04(String key){
        System.out.println("Key: "+ key);
        search.element(SearchBar.input).sendKeys(key);
		search.element(SearchBar.input).submit();
		thinkTime(3000);
		if(result.doesElementExist(ResultPage.noneResult)) {
			assertTrue(result.element(ResultPage.noneResult).getText().trim().startsWith("0 items found for keyword"));
		} else if(result.doesElementExist(ResultPage.resultText)) {
			assertTrue(result.element(ResultPage.resultText).getText().endsWith("Products found"));
		}
    }
    
    //不支持空格输入作为关键字
    @Test(enabled=true)
	public void testCase04_2() {
		search.element(SearchBar.input).sendKeys("    ");
		thinkTime(3000);
		assertEquals("true", search.element(SearchBar.find).getAttribute("disabled"));
		assertFalse(result.doesElementExist(ResultPage.resultText));
		assertFalse(result.doesElementExist(ResultPage.noneResult));
	}
    
    //TC05 搜索时对关键字长度的检验
    @DataProvider(name="keys05")
    public Object[][] Keys05(){
        return new Object[][]{
                {"1"},
                {"123"},
                {"123456890123456890123456890123456890123456890123456890123456890123456890123456890123456890"},
                {"1234568901234568901234568901234568901234568901234568901234568901234568901234568901234568901"}
        };
    }
    
    @Test(dataProvider="keys05", enabled=true)
    public void testCase05(String key){
        System.out.println("Key: "+ key);
        search.element(SearchBar.input).sendKeys(key);
		search.element(SearchBar.input).submit();
		thinkTime(3000);
		if(result.doesElementExist(ResultPage.noneResult)) {
			assertTrue(result.element(ResultPage.noneResult).getText().trim().startsWith("0 items found for keyword"));
		} else if(result.doesElementExist(ResultPage.resultText)) {
			assertTrue(result.element(ResultPage.resultText).getText().endsWith("Products found"));
		}
    }
	  
}
