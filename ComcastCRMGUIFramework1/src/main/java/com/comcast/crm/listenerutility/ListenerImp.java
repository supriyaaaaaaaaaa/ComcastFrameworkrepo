package com.comcast.crm.listenerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClass;

public class ListenerImp implements ITestListener, ISuiteListener {
	
	

	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("Report comnfiguration");
		
		


	}

	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("Report backup");
		
		
	}

	public void onTestStart(ITestResult result) {
		System.out.println("=======" + result.getMethod().getMethodName() + "=====START======");// here before executing
																								// every test script
																								// onTeststart
																								// is going to display
																								// name of the test case
																								// in console
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("=======" + result.getMethod().getMethodName() + "=====END======");
	}

	public void onTestFailure(ITestResult result) {
		String testname = result.getMethod().getMethodName();// here inside this variable(testname) we have failed test
																// case,
																// and as i want to take screenshot of failed test case

		// write screenshot program

		// step1:create an object to Eventfiring Webdriver

		EventFiringWebDriver edriver = new EventFiringWebDriver(BaseClass.sdriver);

		// step2:use getscreenshotAS method to get file type of screenshot
		File srcfile = edriver.getScreenshotAs(OutputType.FILE);
		String	time=new Date().toString().replace(" ", "_").replace(":", "_");

		// step3:store screenshot on local driver
		try {
			FileUtils.copyFile(srcfile, new File("./Screenshots/" + testname + "+"+time+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
