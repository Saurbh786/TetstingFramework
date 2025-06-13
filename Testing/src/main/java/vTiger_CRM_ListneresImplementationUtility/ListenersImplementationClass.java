package vTiger_CRM_ListneresImplementationUtility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import vTiger_CRM_genericJavaUtility.JavaUtility;
import vTiger_CRM_genericWebdriverUtility.UtilityClassObject;

public class ListenersImplementationClass implements ITestListener,ISuiteListener {
	public ExtentSparkReporter spark;
	public  ExtentReports report;
	public ExtentTest test;
    
	@Override
	public void onStart(ISuite suite) {
		JavaUtility jutil = new JavaUtility();
		String currentTime = jutil.getSystemDateAndTimeForScreenshot();
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report_"+currentTime+".html");
		spark.config().setDocumentTitle("vTiger_CRM Test Suite Result");
		spark.config().setReportName("vTiger_CRM Report");
		spark.config().setTheme(Theme.DARK);
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows 10");
		report.setSystemInfo("BROWSER", "chrome");
	}

	@Override
	public void onFinish(ISuite suite) {
		report.flush();
		
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		test = report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		UtilityClassObject.getTest().log(Status.INFO, result.getMethod().getMethodName()+"--Started-----");
		
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		UtilityClassObject.getTest().log(Status.INFO,result.getMethod().getMethodName()+"--Completed-----" );
		
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		TakesScreenshot edriver = (TakesScreenshot)UtilityClassObject.getDriver();
		String scrFile = edriver.getScreenshotAs(OutputType.BASE64);
		JavaUtility jutil = new JavaUtility();
		String currentTime = jutil.getSystemDateAndTimeForScreenshot();
		test.addScreenCaptureFromBase64String(scrFile, testName+currentTime);
		UtilityClassObject.getTest().log(Status.INFO,result.getMethod().getMethodName()+"--Failed-----" );
	}

	

	@Override
	public void onTestSkipped(ITestResult result) {
		UtilityClassObject.getTest().log(Status.INFO,result.getMethod().getMethodName()+"--Skipped-----" );
	}

	
	

}
