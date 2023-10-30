package genericUtilities;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenersImplementationClass implements ITestListener
{
	//Globally declaring ExtentReport and ExtentTest  
	ExtentReports report;
	ExtentTest test;

	public void onTestStart(ITestResult result) 
	{
		// TODO Auto-generated method stub
		String testScriptName = result.getMethod().getMethodName();
		System.out.println(testScriptName+"====Test script execution started====");
		
		//Create a test script - recognize each @Test
		test = report.createTest(testScriptName);
				
	}

	public void onTestSuccess(ITestResult result) 
	{
		// TODO Auto-generated method stub
		String testScriptName = result.getMethod().getMethodName();
		System.out.println(testScriptName+"====Test script execution pass====");
		
		//Log the success
		test.log(Status.PASS, testScriptName+ "Pass");
	}

	public void onTestFailure(ITestResult result) 
	{
		// TODO Auto-generated method stub
		//Screenshot
		//Exception for failure
		
		String testScriptName = result.getMethod().getMethodName();
		System.out.println(testScriptName+"====Test script execution failed====");
		
		//Exception for failure
		System.out.println(result.getThrowable());
		//Log for failure
		test.log(Status.FAIL, testScriptName+"Fail");
		test.log(Status.INFO, result.getThrowable());
		
		//Screenshot
		String screenShotName = testScriptName+new JavaUtility().getSystemDate();
		WebDriverUtility w = new WebDriverUtility();
		try 
		{
			String path = w.captureScreenshot(BaseClass.sdriver, screenShotName);
			test.addScreenCaptureFromPath(path);
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void onTestSkipped(ITestResult result) 
	{
		// TODO Auto-generated method stub
		String testScriptName = result.getMethod().getMethodName();
		System.out.println(testScriptName+"====Test script execution skipped====");
		
		//Exception for failure
		System.out.println(result.getThrowable());
		//Log for skip
		test.log(Status.SKIP, testScriptName+" == skipped ==");
		test.log(Status.INFO, result.getThrowable());
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedWithTimeout(ITestResult result) 
	{
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) 
	{
		// TODO Auto-generated method stub
		System.out.println("====Suite execution started====");
		
		//Basic report configuration
		ExtentSparkReporter html = new ExtentSparkReporter(".\\ExtentReports\\Report-"+new JavaUtility().getSystemDate()+".html");
		html.config().setTheme(Theme.DARK);
		html.config().setDocumentTitle("Execution Report");
		html.config().setReportName("VTiger execution report");
		
		report = new ExtentReports();
		report.attachReporter(html);
		report.setSystemInfo("Base Platform", "Windows");
		report.setSystemInfo("Base Browser", "Chrome");
		report.setSystemInfo("Base Environment", "Testing");
		report.setSystemInfo("Reporter Name", "Rajendra");
		
	}

	public void onFinish(ITestContext context) 
	{
		// TODO Auto-generated method stub
		System.out.println("====Suite execution finished====");
		
		//Report generation
		report.flush();
	}
	
	

}
