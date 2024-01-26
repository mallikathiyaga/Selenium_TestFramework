package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import base.BaseTest;
import junit.framework.Assert;
import utils.ElementFetch;

public class InventoryPageElements extends HomePageElements{

	
	 public InventoryPageElements (WebDriver driver) { 
		 super(driver);
		 	 }
	 
		
	
	@FindBy(how = How.ID, using = "add-to-cart-sauce-labs-backpack")
	public WebElement addToCartBackPack;
	@FindBy(how = How.ID, using = "remove-sauce-labs-backpack")
	public WebElement removeBackPack;
	@FindBy(how = How.XPATH, using = "//span[@class='shopping_cart_badge']")
	public WebElement shoppingCart;
	
	
	
	

	public void addToCart()
	{
		this.addToCartBackPack.click();
		logger.log(Status.INFO , "Added to cart");
		/*
		 * ele.getWebElement("ID",HPele.username).sendKeys(username);
		 * ele.getWebElement("ID",HPele.password).sendKeys(password);
		 * ele.getWebElement("ID",HPele.login).click();
		 */
		}

	public void verifyAddToCart()
	{
			Assert.assertTrue(this.removeBackPack.isDisplayed());
			//Assert.assertTrue(shoppingCart.getText());
			Assert.assertEquals(shoppingCart.getText(), "1");
			
		}
		
				

}
