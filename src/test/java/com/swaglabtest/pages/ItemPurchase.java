package com.swaglabtest.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class ItemPurchase {
	
	WebDriver driver;
	WebDriverWait wait;
	
	public ItemPurchase(WebDriver driver)
	{
		this.driver=driver;
	     wait = new WebDriverWait(driver, Duration.ofSeconds(40));
	}
	
	/*----------- list of Elements-----*/

	private final By Sauce_Labs_Backpack=
			 By.xpath("//a[@id='item_4_title_link']/div");
	private final By Sauce_Labs_Backpack_Price=
			By.xpath("//a[@id='item_4_title_link']/parent::*/following-sibling::*/div");
	private final By Add_Sauce_Labs_Backpack_button = 
			By.xpath("//a[@id='item_4_title_link']/parent::*/following-sibling::*/button");	
	
	private final By Sauce_Labs_Bolt_T_Shirt = 
			By.xpath("//a[@id='item_1_title_link']/div");
	private final By Sauce_Labs_Bolt_T_Shirt_Price = 
			By.xpath("//a[@id='item_1_title_link']/parent::*/following-sibling::*/div");
	private final By Add_Sauce_Labs_Bolt_T_Shirt_Price_button = 
			By.xpath("//a[@id='item_1_title_link']/parent::*/following-sibling::*/button");
	
	private final By Sauce_Labs_Onesie = 
			By.xpath("//a[@id='item_2_title_link']/div");
	private final By Sauce_Labs_Onesie_Price =
			By.xpath("//a[@id='item_2_title_link']/parent::*/following-sibling::*/div");
	private final By Add_Sauce_Labs_Onesie_button =
			By.xpath("//a[@id='item_2_title_link']/parent::*/following-sibling::*/button");
	
	private final By Test_allTheThing_T_Shirt_Red = 
			By.xpath("//a[@id='item_3_title_link']/div");
	private final By Test_allTheThing_T_Shirt_Red_Price =
			By.xpath("//a[@id='item_3_title_link']/parent::*/following-sibling::*/div");
	private final By Add_Test_allTheThing_T_Shirt_Red_Button = 
			By.xpath("//a[@id='item_3_title_link']/parent::*/following-sibling::*/button");
	
	private final By Sauce_Labs_Fleece_Jacket = 
	 By.xpath("//a[@id='item_5_title_link']/div");
	private final By Sauce_Labs_Fleece_Jacket_Price =
			By.xpath("//a[@id='item_5_title_link']/parent::div/following-sibling::div/div");
	private final By Sauce_Labs_Fleece_Jacket_button = 
			By.xpath("//a[@id='item_5_title_link']/parent::div/following-sibling::div/button");
	
	
	private final By droxpwonSort=By.xpath("//select[@class='product_sort_container']");
	
	private final By shopcart = 
			By.xpath("//*[@class='shopping_cart_link']");
	private final By shopping_cart_badge =
			By.xpath("//*[@class='shopping_cart_badge']");
	
	
	private final By hangbutton=
			By.xpath("//button[@id=\"react-burger-menu-btn\"]");
	
	private final By logOut=
			By.xpath("//a[@id=\"logout_sidebar_link\"]");
	
	
	//---- Action Methods-----//
	
	
		public Map<String, Item> getItemsMap() {

		    Map<String, Item> items = new HashMap<>();

		    items.put("Sauce_Labs_Backpack", new Item(
		            Sauce_Labs_Backpack,
		            Sauce_Labs_Backpack_Price,
		            Add_Sauce_Labs_Backpack_button));

		    items.put("Sauce_Labs_Bolt_T_Shirt", new Item(
		            Sauce_Labs_Bolt_T_Shirt,
		            Sauce_Labs_Bolt_T_Shirt_Price,
		            Add_Sauce_Labs_Bolt_T_Shirt_Price_button));

		    items.put("Sauce_Labs_Onesie", new Item(
		            Sauce_Labs_Onesie,
		            Sauce_Labs_Onesie_Price,
		            Add_Sauce_Labs_Onesie_button));

		    items.put("Sauce_Labs_Fleece_Jacket", new Item(
		            Sauce_Labs_Fleece_Jacket,
		            Sauce_Labs_Fleece_Jacket_Price,
		            Sauce_Labs_Fleece_Jacket_button));

		    items.put("Test_allTheThing_T_Shirt_Red", new Item(
		            Test_allTheThing_T_Shirt_Red,
		            Test_allTheThing_T_Shirt_Red_Price,
		            Add_Test_allTheThing_T_Shirt_Red_Button));

		    return items;
		}

		
		public boolean newPurchasing(String itemName) {
			boolean result =false;
		    
			Map<String, Item> items = getItemsMap();
			Item item = items.get(itemName);

		    if (item == null) {
		        System.out.println("Item not found: " + itemName);
		        result=false;
		    }
		    WebElement nameElement = wait.until(
		            ExpectedConditions.visibilityOfElementLocated(item.getName()));
            System.out.println(nameElement);
		    WebElement priceElement = wait.until(
		            ExpectedConditions.visibilityOfElementLocated(item.getPrice()));

		    WebElement buttonElement = wait.until(
		            ExpectedConditions.elementToBeClickable(item.getButton()));

		    System.out.println("Add Item: " + nameElement.getText());
		    System.out.println("Add Price: " + priceElement.getText());

		    if (buttonElement.getText().equalsIgnoreCase("Add to cart")) {
		        buttonElement.click();
		        result=true;
		    }
			return result;
		}
		
		
		public boolean DeletePurchasing(String ItemName)
		{  Map<String, Item> items = getItemsMap();
		   Item item = items.get(ItemName);
		   boolean result =false;
		   
			 if (item == null) {
			        System.out.println("Item not found: " + ItemName);
			        result=false;
			    }
			 WebElement buttonElement = wait.until(ExpectedConditions.elementToBeClickable(item.getButton()));
			 
			 if(buttonElement.getText().equals("Remove"))
			 {
				 System.out.println("Remove Item: " +ItemName);
				 buttonElement.click();
			        result=true;
			 }
			 ;
			return result;
		}
	
	public void acceptAlert()
	{
		driver.switchTo().alert().accept();
	}
	
	public void clickShopCart()
	{
		 WebElement itemcart=wait.until(ExpectedConditions.elementToBeClickable(shopcart));
		 itemcart.click();
	}
	
	public String ShopCartBatch()
	{
		WebElement shopping_cart_b=wait.until(ExpectedConditions.elementToBeClickable(shopping_cart_badge));
		return shopping_cart_b.getText();
	}
	
	public void dropDownSelection(String selectedText)
	{
		Select dropdown = new Select(wait.until(ExpectedConditions.elementToBeClickable(droxpwonSort)));
		dropdown.selectByVisibleText(selectedText);
	}
	
	public void dropDownSelection(int intID)
	{
		Select dropdown = new Select(wait.until(ExpectedConditions.elementToBeClickable(droxpwonSort)));
		dropdown.selectByIndex(intID);
	}
	
	public List<WebElement> getAllLinks()
	{
	  List<WebElement> listelements=driver.findElements(By.tagName("link"));
	  return listelements;
	}
	
	public void clickHangButton()
	{
		driver.findElement(hangbutton).click();
		
	}
	
	public void clickLogoutButton()
	{
		driver.findElement(logOut).click();
		
	}
	
	
	public boolean listcollectionAscending()
	{
		boolean result = false;
		
		dropDownSelection("Name (A to Z)");
		
		List<WebElement> itemlist = driver.findElements(By.className("inventory_item_name"));
		List<String> itemname = new ArrayList<>();
		for (WebElement item : itemlist) {
			itemname.add(item.getText());
		}
		System.out.println("Items in the List: " + itemname);
		List<String> itemlist2 =new ArrayList<String>(itemname);
		Collections.sort(itemlist2);
		
		for (int i = 0; i <= itemlist2.size() - 1; i++) 
		{
			System.out.println("Sorted List: " + itemlist2.get(i));
			
			if (itemname.get(i).equals(itemlist2.get(i))) 
			{
				System.out.println("Items are in Accending order");
				result=true;
			} else {
				System.out.println("Items are not in Accending order");
				result=false;
				break;
			}
		}
		return result;
	}
	
	public boolean listcollectionDescending() {
		boolean result = false;
		dropDownSelection("Name (Z to A)");
		List<WebElement> elements = driver.findElements(By.className("inventory_item_name"));
		List<String> listname = new ArrayList<String>();

		for (WebElement element : elements) {
			listname.add(element.getText());
		}
		List<String> itemlistNew = new ArrayList<String>(listname);
		Collections.sort(itemlistNew,Collections.reverseOrder());
		
		for (int i = 0; i <= itemlistNew.size() - 1; i++) {
			System.out.println("Sorted List: " + itemlistNew.get(i));

			if (listname.get(i).equals(itemlistNew.get(i))) {
				System.out.println("Items are in Descending order");
				result = true;
			} else {
				System.out.println("Items are not in Descending order");
				result = false;
				break;
			}
		}

		return result;
	}
	
	public boolean lowerToHighListdown() {
		dropDownSelection("Price (low to high)");
		boolean result = false;
		List<WebElement> priceElements = driver.findElements(By.className("inventory_item_price"));
		List<Double> actualPrices = new ArrayList<>();
		// Store actual prices
		for (WebElement price : priceElements) {
			// Remove $ symbol and convert to double
			String priceText = price.getText().replace("$", "");
			actualPrices.add(Double.parseDouble(priceText));
		}

		// Create expected sorted list
		List<Double> expectedPrices = new ArrayList<>(actualPrices);
		Collections.sort(expectedPrices);
		for (int i = 0; i <= expectedPrices.size() - 1; i++) {

			if (actualPrices.get(i).equals(expectedPrices.get(i))) {
				System.out.println("Prices are sorted Lower to High");
				result = true;
			} else {
				System.out.println("Prices are NOT sorted correctly");
				result = false;
				break;
			}
		}

		return result;
	}
	
	
	public boolean HightoLowerListDown()
	{
	  dropDownSelection("Price (high to low)");
	  boolean result=false;
	  List<WebElement> priceElements = driver.findElements(By.className("inventory_item_price"));
	  List<Double> actualPrices = new ArrayList<>();
	  // Store actual prices
	  for (WebElement price : priceElements) {
	      // Remove $ symbol and convert to double
	      String priceText = price.getText().replace("$", "");
	      actualPrices.add(Double.parseDouble(priceText));
	  }

	  // Create expected sorted list
	  List<Double> expectedPrices = new ArrayList<>(actualPrices);
	  Collections.sort(expectedPrices,Collections.reverseOrder());
	  
	  for(int i=0;i<=expectedPrices.size()-1;i++)
	  {
		 
		  if(actualPrices.get(i).equals(expectedPrices.get(i))) {
		      System.out.println("Prices are sorted High to lower");
		      result=true;
		  }
		  else {
		      System.out.println("Prices are NOT sorted correctly");
		      result=false;
		      break;
		  }  
	  }
	  
	 return result;  
	}
	
	
}

