package com.swaglabtest.testcases;

import java.lang.reflect.Method;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.swaglabtest.pages.BaseClass;
import com.swaglabtest.pages.ItemPurchase;
import com.swaglabtest.pages.MainLoginPage;

public class BrokenLinkTest extends BaseClass {

	//BaseClass bs = new BaseClass();

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

	@Test(priority = 1)
	public void brokenLinkTestLoginPage() {
		SoftAssert softAssert = new SoftAssert();
		System.out.println("current URL :" + driver.getCurrentUrl());
		List<WebElement> links = login.getAllLinks();
		for (WebElement element : links) {

			String url = element.getAttribute("href");

			if (url == null || url.isEmpty())  {
				continue;
			}
			
			if (url.matches(".*\\.(png|jpg|jpeg|gif|svg)$"))  {
				continue;
			}

			int responseCode = LinkValidator.validateLink(url);
			if (responseCode >= 400) {
				System.out.println("Broken URL " + url + " : " + responseCode);
				softAssert.fail("Broken URL " + url + " : " + responseCode);
			} else {
				// System.out.println( url + " : " + responseCode);
				System.out.println("URL is valid " + url + " : " + responseCode);
			}

		}
		softAssert.assertAll();
	}

	@Test(priority = 2)
	public void brokenLinkTestPurchasePage() {
		SoftAssert softAssert = new SoftAssert();
		login.UserLogin(excelp.getStringdata("Login", 2, 1), excelp.getStringdata("Login", 2, 2));
		purchase = PageFactory.initElements(driver, ItemPurchase.class);
		System.out.println("current URL :" + driver.getCurrentUrl());
		List<WebElement> links = purchase.getAllLinks();
		for (WebElement element : links) {

			String url = element.getAttribute("href");

			if (url == null || url.isEmpty())  {
				continue;
			}
			
			if (url.matches(".*\\.(png|jpg|jpeg|gif|svg)$"))  {
				continue;
			}

			int responseCode = LinkValidator.validateLink(url);
			if (responseCode >= 400) {
				System.out.println("Broken URL " + url + " : " + responseCode);
				softAssert.fail("Broken URL " + url + " : " + responseCode);
			} else {
				System.out.println("URL is valid " + url + " : " + responseCode);
			}

		}
		softAssert.assertAll();
	}

}
