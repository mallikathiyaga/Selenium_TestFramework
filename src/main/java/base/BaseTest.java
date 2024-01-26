package base;

import org.testng.annotations.*;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Constants;
import utils.ReadXLSdata;
import utils.ThreadFileOutPut;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.*;



public class BaseTest implements ITestListener {
	public static WebDriver driver;
	public ExtentReports extent;
	public ExtentTest logger;
	public ExtentSparkReporter sparkReporter;
	
	String consoleOutputLocation = System.getProperty("user.dir") + File.separator + "logs" + File.separator + getClass().getSimpleName()+ ".txt";
	
	@DataProvider(name="dataExcel")
	public Object [][] dataLoad(Method method) {
		String testCaseName = method.getName();
		Class <?>[] parameterTypes = method.getParameterTypes();
		
		Object [][] testData = null;
		try {
			testData = ReadXLSdata.getTestData(testCaseName,parameterTypes);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testData;}

	@BeforeTest
	public void beforeTestMethod() {
		// Initialize extent report
		sparkReporter = new ExtentSparkReporter(
		//		System.getProperty("user.dir") + File.separator + "reports" + File.separator + "Test.html")
		System.getProperty("user.dir") + File.separator + "reports" + File.separator + this.getClass().getSimpleName() + ".html");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

	}

	@BeforeMethod
	public void beforeMethod(Method testMethod) throws Exception{
		logger = extent.createTest(testMethod.getName());
		ThreadFileOutPut.startThreadOutputRedirect(new FileOutputStream(new File(consoleOutputLocation)));
		
				/*
		 * WebDriverManager.chromedriver().setup(); driver = new ChromeDriver();
		 * driver.manage().window().maximize(); driver.get(Constants.URL);
		 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		 * System.out.println("Browser launched"); return driver;
		 */
	}
	
	
	

	
	public WebDriver launchBrowser() {
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--ignore-certificate-errors");
		//options.addArguments("--headless");
		options.setExperimentalOption("excludeSwitches", new String [] {"enable-automation"});
		//options.setExperimentalOption("excludeSwitches", "enable-automation");
		
		driver = new ChromeDriver(options);
		//driver.manage().window().maximize();
		
		driver.get(Constants.URL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		return driver;
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			// logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() ,
			// ExtentColor.RED));
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
			logger.log(Status.FAIL,"Console Output" + "<a href='file:///" + consoleOutputLocation + "' target='_blank'>link</a>");
			//logger.fail("Test Case failed check screenshot below  "+
			logger.addScreenCaptureFromPath(System.getProperty("user.dir")+ File.separator+ "screenshots"+File.separator+result.getMethod().getMethodName() + ".png");
			
			
		}
		if (result.getStatus() == ITestResult.SKIP)
			logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.ORANGE));

		if (result.getStatus() == ITestResult.SUCCESS)
			logger.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
		driver.quit();
	}

	@AfterTest
	public void afterTest() {
		extent.flush();
	}
}
