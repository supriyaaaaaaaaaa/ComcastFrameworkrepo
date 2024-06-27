package com.comcast.crm.listenerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListenerImpforusingextentReport implements ITestListener, ISuiteListener{
	
	
	// for extent reports
	    public ExtentSparkReporter spark;
        public  static ExtentReports report; //if you want to use this report in every test case so make this variable static
        public  ExtentTest test;


	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("Report comnfiguration");
		 String	time=new Date().toString().replace(" ", "_").replace(":", "_");
		
		// spark report config
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report_"+time+".html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// add Env information and create Test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("os", "Window-10");
		report.setSystemInfo("BROWSER", "CHROME-100");


	}

	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("Report backup");
		
		report.flush();

	}

	public void onTestStart(ITestResult result) {
		System.out.println("=======" + result.getMethod().getMethodName() + "=====START======");// here before executing
																								// every test script
																								// is going to display
																								// name of the test case
																								// in console
		

		  test = report.createTest(result.getMethod().getMethodName());
		  
		  UtilityClassObject.setTest(test); 
		  test.log(Status.INFO, result.getMethod().getMethodName()+"===STARTED===");
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("=======" + result.getMethod().getMethodName() + "=====END======");
		  test.log(Status.PASS, result.getMethod().getMethodName()+"===COMPLETED===");

	}

	public void onTestFailure(ITestResult result) {
		String testname = result.getMethod().getMethodName();// here inside this variable(testname) we have failed test
																// case,
																// and as i want to take screenshot of failed test case

 		// write screenshot program
          TakesScreenshot eDriver=(TakesScreenshot)BaseClass.sdriver;
		 
		 //whenever we use Base64 Screenshot it is going to return the screenshot location, so inside this (filepath) variable we have screenshot location
		 String filepath = eDriver.getScreenshotAs(OutputType.BASE64);//because Extentsreports sUpports Base64 screenshot
		 
		 String	time=new Date().toString().replace(" ", "_").replace(":", "_");
		 test.addScreenCaptureFromBase64String(filepath, testname +" "+time);
		  test.log(Status.PASS, result.getMethod().getMethodName()+"===FAILED===");

		 

		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {

	}
	// TODO Auto-generated method stub

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	
	

}}



