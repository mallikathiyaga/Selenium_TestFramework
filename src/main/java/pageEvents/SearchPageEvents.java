package pageEvents;

import junit.framework.Assert;
import pageObjects.SearchPageElements;
import utils.ElementFetch;

public class SearchPageEvents {

	ElementFetch ele = new ElementFetch();
	SearchPageElements SPele = new SearchPageElements();
	
	public void verifySearchResultLoaded()
	{
		Assert.assertTrue(ele.getWebElement("XPATH", SPele.SearchResults).isDisplayed());
		
		//Assert.assertTrue("Element not found" , ele.getWebElements("XPATH", SPele.SearchResults).size()==0);
		
		
	}
}
