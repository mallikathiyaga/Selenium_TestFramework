package QATests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import base.BaseTest;
import pageEvents.HomePageEvents;
import pageEvents.SearchPageEvents;
import pageObjects.HomePageElements;
import pageObjects.SearchPageElements;
import utils.ElementFetch;
import utils.ReadXLSdata;

public class GoogleSearch extends BaseTest{
	
	ElementFetch ele = new ElementFetch();
	SearchPageEvents SPeve = new SearchPageEvents();
	HomePageEvents HPeve = new HomePageEvents();
  @Test(dataProviderClass=ReadXLSdata.class,dataProvider = "dataExcel")
  public void HitGoogleSearch (String TestCaseName, String SearchPattern, String ResultPattern) throws EncryptedDocumentException, IOException {
	  
		/*
		 * ReadXLSdata rwd = new ReadXLSdata(); String testData[][] =
		 * rwd.getTestData(HitGoogleSearch);
		 */
	  System.out.println("SearchPattern :" + SearchPattern);
	  
	  HPeve.search(SearchPattern);
	 // System.out.println("Search has been hit");
	  System.out.println("REsultPattern :" +	ResultPattern);
	  SPeve.verifySearchResultLoaded();
	 // System.out.println("Search successful");
	  
  }
  
  @Test
  public void HitGoogleSearchWithoutDataProvider (String TestCaseName, String SearchPattern, String ResultPattern) throws EncryptedDocumentException, IOException {
	  
		
		 ReadXLSdata rwd = new ReadXLSdata(); 
		 String testData[][] = rwd.getTestData(null);
		
	  System.out.println("SearchPattern :" + SearchPattern);
	  
	  HPeve.search(SearchPattern);
	 // System.out.println("Search has been hit");
	  System.out.println("REsultPattern :" +	ResultPattern);
	  SPeve.verifySearchResultLoaded();
	 // System.out.println("Search successful");
	  
  }
}
