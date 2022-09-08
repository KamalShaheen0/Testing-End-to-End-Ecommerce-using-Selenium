package KamalProject.TestComponent;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import KamalProject.Resources.ExtentReporterNG;



public class Listeners extends BaseTest implements ITestListener  {
	
	ExtentTest test ;
	ExtentReports extent =ExtentReporterNG.getReportObject(); 
	
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>() ;//Thread safe
	
	 @Override
	  public void onTestStart(ITestResult result) {
		 test = extent.createTest(result.getMethod().getMethodName());
		 extentTest.set(test);//unique thread id(ErrorVaildationTest)-->test
	  }

	  @Override
	  public void onTestSuccess(ITestResult result) {
		  extentTest.get().log(Status.PASS, "Test Passed");
	  }

	  @Override
	  public void onTestFailure(ITestResult result) {
		  
		  
		  //test.log(Status.FAIL, "Test Failed");
		  
	  try {
		driver =(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
	 } catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} //get life to driver to take ScreenShot
	  
	  
	  
	   extentTest.get().fail(result.getThrowable());
		String filePath = null;
		try {
			filePath = getScreenShot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		  
		  
	  }

	  @Override
	  public void onTestSkipped(ITestResult result) {
	  }

	  @Override
	  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	 
	  }
	  
	  @Override
	  public void onFinish(ITestContext context) {
		  extent.flush();  	
		  
	  }
	
	 
	 

}