package QATests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.BaseTest;
import beans.SwagLabs_TestData;
import pageObjects.HomePageElements;
import pageObjects.InventoryPageElements;

public class SwagLabsAddToCart extends BaseTest{
	
	/*
	 * InventoryPageElements Ipe;
	 * 
	 * @BeforeClass public void setUp()
	 * 
	 * {driver = super.launchBrowser(); logger.log(Status.INFO ,
	 * "Browser launched  "); Ipe = new InventoryPageElements(driver);}
	 */
	
	
	
	@Test(dataProvider = "dataExcel")
	 
	public  void AddToCart(SwagLabs_TestData td) throws InterruptedException{
		
		InventoryPageElements Ipe;
		
		driver = super.launchBrowser();
		logger.log(Status.INFO , "Browser launched  ");
		Ipe = new InventoryPageElements(driver);
	//	HomePageElements HPele = new HomePageElements(driver);
		String username = td.getUserName();
		String password = td.getPassword();
		//HPele.login("standard_user", "secret_sauce");
		Ipe.login(username, password);
		//driver.wait(10000);
		System.out.println("Login successful " );
		Ipe.addToCart();
		logger.log(Status.INFO , "Added to cart");
		Ipe.verifyAddToCart();
		logger.log(Status.INFO , "verified Added to cart");
	
	};
	
}
