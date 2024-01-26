package QATests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.BaseTest;
import beans.SwagLabs_TestData;
import pageEvents.HomePageEvents;
import pageEvents.SearchPageEvents;
import pageObjects.HomePageElements;
import utils.ElementFetch;
import utils.ReadXLSdata;

public class SwagLabsLogin extends BaseTest {

	@Test(dataProvider = "dataExcel")
	 
	public  void Login_01(SwagLabs_TestData td){
		//ElementFetch ele = new ElementFetch();
		//HomePageEvents HPeve = new HomePageEvents();
		driver = super.launchBrowser();
		logger.log(Status.INFO , "Browser launched  ");
		HomePageElements HPele = new HomePageElements(driver);
		System.out.println("Before Login method username :" + 	HPele.password);
		String username = td.getUserName();
		String password = td.getPassword();
		//HPele.login("standard_user", "secret_sauce");
		HPele.login(username, password);
		logger.log(Status.INFO , "Login completed ");
		HPele.verifySuccesslogin();
		System.out.println("First testcase completed");
	};
	
	@Test(dataProvider = "dataExcel")
	public  void Login_02(SwagLabs_TestData td){
		driver = super.launchBrowser();
		HomePageElements HPele = new HomePageElements(driver);
		//System.out.println("Before Login method username :" + 	HPele.password);
		String username = td.getUserName();
		String password = td.getPassword();
		//HPele.login("standard_user", "secret_sauce");
		HPele.login(username, password);
		HPele.verifySuccesslogin();
	};


}
