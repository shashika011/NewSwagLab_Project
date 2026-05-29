package com.swaglabtest.testcases;

import java.lang.reflect.Method;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.swaglabtest.pages.BaseClass;
import com.swaglabtest.pages.ItemPurchase;
import com.swaglabtest.pages.MainLoginPage;

public class SeccurityTest extends BaseClass{

	MainLoginPage login;
	ItemPurchase purchase;

	@BeforeMethod
	public void PageIninitialize(Method method) {
		login = PageFactory.initElements(driver, MainLoginPage.class);
		// System.out.println(method.getName());
		String className = method.getDeclaringClass().getSimpleName();
		String methodName = method.getName();

		logger = report.createTest(className + " - " + methodName);
		logger.info("Starting Test: " + className + " → " + methodName);
	}
    @Test(priority=1)
	public void VerifyWithoutLogin()
	{
		driver.get("https://www.saucedemo.com/inventory.html");
		String currentUrl = driver.getCurrentUrl();
		if (currentUrl.equals("https://www.saucedemo.com/inventory.html")) {
			Assert.fail("User is able to access inventory page without login.");
		} else {
			Assert.assertTrue(true, "Security issue: user accessed inventory without login");
		}
		
	}
	@Test(priority=2)
    public void checkLogoutUser()
    {
    	login.UserLogin(excelp.getStringdata("Login", 2, 1), excelp.getStringdata("Login", 2, 2));
    	purchase = PageFactory.initElements(driver, ItemPurchase.class);
    	purchase.clickHangButton();
    	purchase.clickLogoutButton();
    	driver.navigate().back();
    	String currentUrl1 = driver.getCurrentUrl();
    	System.out.println(currentUrl1);
		if (currentUrl1 != "https://www.saucedemo.com/inventory.html") {
			Assert.assertTrue(true, "User is successfully logged out and cannot access inventory page.");
		} else {
			Assert.fail("Security issue: user can access inventory page after logout.");
		}
    	
    }
    
    
    
}
