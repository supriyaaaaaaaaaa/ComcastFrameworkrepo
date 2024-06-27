package com.comcast.crm.basetest;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass {

	// create object excel file class
	public ExcelUtility elib = new ExcelUtility();
	public DataBaseUtility dlib = new DataBaseUtility();
	public FileUtility flib = new FileUtility();
	public JavaUtility jlib = new JavaUtility();
	public WebDriverUtility wlib = new WebDriverUtility();
	public WebDriver driver = null;

	// for listener
	public static WebDriver sdriver = null;

//	// for extent reports
//	public ExtentReports report;

	@BeforeSuite(groups = { "smokeTest", "regressionTest" })
	public void configAS() throws SQLException {
		System.out.println("===Connect to DB,Report config===");
		dlib.getDbconnection();

//		// spark report config
//		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report.html");
//		spark.config().setDocumentTitle("CRM Test Suite Results");
//		spark.config().setReportName("CRM Report");
//		spark.config().setTheme(Theme.DARK);
//
//		// add Env information and create Test
//		report = new ExtentReports();
//		report.attachReporter(spark);
//		report.setSystemInfo("os", "Window-10");
//		report.setSystemInfo("BROWSER", "CHROME-100");

	}

	@BeforeClass(groups = { "smokeTest", "regressionTest" })
	public void configBc() throws Exception {
		System.out.println("===Launch the BROWSER===");
		String BROWSER = flib.getDataFromPropertiesFile("browser");

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}
		// for listener
		sdriver = driver;

		UtilityClassObject.setDriver(driver);
	}

	@BeforeMethod(groups = { "smokeTest", "regressionTest" })
	public void configBM() throws Exception {

		System.out.println("==Login==");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");

		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(URL, USERNAME, PASSWORD);

	}

	@AfterMethod(groups = { "smokeTest", "regressionTest" })
	public void configAM() {
		System.out.println("==Logout==");
		HomePage hp = new HomePage(driver);
		hp.logout();
	}

	@AfterClass(groups = { "smokeTest", "regressionTest" })
	public void configAc() {
		System.out.println("===Close the BROWSER===");
		driver.quit();
	}

	@AfterSuite(groups = { "smokeTest", "regressionTest" })
	public void configBS() throws SQLException {
		System.out.println("===close Db,Report Backup===");
		dlib.closeDbconnection();

		// report.flush();
	}

}
