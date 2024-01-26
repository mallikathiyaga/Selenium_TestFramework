package utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import base.BaseTest;


public class SuiteListerner implements ITestListener, IAnnotationTransformer
{
public void onTestFailure(ITestResult result)	
{
	System.out.println("In SuiteListener");
String filename = System.getProperty("user.dir")+ File.separator+ "screenshots"+File.separator+result.getMethod().getMethodName();
File file = ((TakesScreenshot)BaseTest.driver).getScreenshotAs(OutputType.FILE)
;
try {
	FileUtils.copyFile(file, new File(filename + ".png"));
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

}

/*
 * public void transform( ITestAnnotation annotation, Class testClass,
 * Constructor testConstructor, Method testMethod) { // not implemented
 * annotation.setRetryAnalyzer(RetryAnalyzer.class); }
 */
}