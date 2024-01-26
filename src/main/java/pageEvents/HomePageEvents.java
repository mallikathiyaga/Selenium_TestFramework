package pageEvents;

import junit.framework.Assert;
import pageObjects.HomePageElements;
import utils.ElementFetch;
import org.openqa.selenium.support.PageFactory;

import base.BaseTest;

public class HomePageEvents extends BaseTest{

	ElementFetch ele = new ElementFetch();
	HomePageElements HPele = new HomePageElements(driver);
	/*
	 * public void search(String searchPattern) {
	 * HPele.googleSearchBar.sendKeys(searchPattern); HPele.googleSearchBar.click();
	 * //googleSearchBar }
	 */

public void login(String username, String password)
{
	System.out.println(HPele.username);
	HPele.username.sendKeys(username);
	HPele.password.sendKeys(password);
	HPele.login.click();
	/*
	 * ele.getWebElement("ID",HPele.username).sendKeys(username);
	 * ele.getWebElement("ID",HPele.password).sendKeys(password);
	 * ele.getWebElement("ID",HPele.login).click();
	 */
	}

public void verifySuccesslogin()
{
		Assert.assertTrue(HPele.homePageLogo.isDisplayed());
	}
	
			}
