package com.comcast.crm.orgtest;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrganizationTestUsingPOM {

	public static void main(String[] args) throws Exception {
		// create object properties file class
		FileUtility flib = new FileUtility();

		// create object excel file class
		ExcelUtility elib = new ExcelUtility();

		// create object for javautility class
		JavaUtility jlib = new JavaUtility();

		// read common data from properties file
		String URL = flib.getDataFromPropertiesFile("url");
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");

		// read test script data from excel file

		String orgname = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();

		WebDriver driver = null;

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		// step1:login to app
		//driver.get(URL);

		LoginPage lp = new LoginPage(driver);
		// LoginPage lp = PageFactory.initElements(driver,LoginPage.class);

//		lp.getUsernameEdit().sendKeys(USERNAME);
//		lp.getPasswordEdit().sendKeys(PASSWORD);
//		lp.getLoginBtn().click();  

		lp.loginToApp(URL,USERNAME, PASSWORD);

		// step2: click on organization link// Navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();

//		//Navigate to campaign page
//	     hp.getCampaignslink();
//		
		// step3:click on "create organization" Button

		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNeworgBtn().click();

		// step4:enter all the details and create new Organization
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);

		cnop.createorg(orgname);

		// verify Header msg Expected Result

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actorgname = oip.getHeaderMsg().getText();
		if (actorgname.contains(orgname)) {
			System.out.println(orgname + "  is verified==PASS");
		}

		else {
			System.out.println(orgname + "  is verified==FAIL");
		}

		// logout
		hp.logout();
		driver.quit();

	}
}
