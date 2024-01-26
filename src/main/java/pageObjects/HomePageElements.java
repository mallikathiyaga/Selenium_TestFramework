package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import base.BaseTest;
import junit.framework.Assert;
import utils.ElementFetch;

public class HomePageElements extends BaseTest{

	/*
	 * @FindBy(how = How.XPATH, using =
	 * "//input[@value = 'Google Search' and @type = 'submit'])[1]") public
	 * WebElement googleSearchButton;
	 * 
	 * @FindBy(how = How.XPATH, using = "//textarea[@title='Search']") public
	 * WebElement googleSearchBar;
	 */
	// public String googleSearchButton = "(//input[@value = 'Google Search' and
	// @type = 'submit'])[1]";

	// public String googleSearchBar = "//textarea[@title='Search']";

	
	 public HomePageElements (WebDriver driver) { 
	 PageFactory.initElements(driver, this);
	 	 this.driver= driver;
	 }
	 
		/*
		 * public WebDriver getDriver() { return driver;}
		 */
	 
	 //ElementFetch ele = new ElementFetch();
	 
	
	@FindBy(how = How.ID, using = "user-name")
	public WebElement username;
	@FindBy(how = How.ID, using = "password")
	public WebElement password;
	@FindBy(how = How.ID, using = "login-button")
	public WebElement login;
	@FindBy(how = How.XPATH, using = "//div[@class = 'app_logo']")
	public WebElement homePageLogo;
	// public String username = "user-name";
	// public String password = "password";
//	public String login = "login-button";
	// public String homePageLogo = "//class[@app_logo = 'Swag Labs']";

	
	

	public void login(String username, String password)
	{
		System.out.println("In login Method : " + this.login);
		this.username.sendKeys(username);
		this.password.sendKeys(password);
		this.login.click();
		
		System.out.println("Login clicked");
		/*
		 * ele.getWebElement("ID",HPele.username).sendKeys(username);
		 * ele.getWebElement("ID",HPele.password).sendKeys(password);
		 * ele.getWebElement("ID",HPele.login).click();
		 */
		}

	public void verifySuccesslogin()
	{
			Assert.assertTrue(this.homePageLogo.isDisplayed());
		}
		
				

}
