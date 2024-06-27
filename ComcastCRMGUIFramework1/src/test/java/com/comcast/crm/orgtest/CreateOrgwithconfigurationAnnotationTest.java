package com.comcast.crm.orgtest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
@Listeners(com.comcast.crm.listenerutility.ListenerImpforusingextentReport.class)
public class CreateOrgwithconfigurationAnnotationTest extends BaseClass {

	@Test
	public void CreateOrg() throws Exception {
		
	
//		LoginPage lp = new LoginPage(driver);
		// LoginPage lp = PageFactory.initElements(driver,LoginPage.class);

//		lp.getUsernameEdit().sendKeys(USERNAME);
//		lp.getPasswordEdit().sendKeys(PASSWORD);
//		lp.getLoginBtn().click();  

//		lp.loginToApp(URL,USERNAME, PASSWORD);

		// step2: click on organization link// Navigate to organization module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to org page");
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();

//		//Navigate to campaign page
//	     hp.getCampaignslink();
//		
		// step3:click on "create organization" Button
		UtilityClassObject.getTest().log(Status.INFO, "navigate to  create org page");


		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNeworgBtn().click();
		
		UtilityClassObject.getTest().log(Status.INFO, "read data from Excel");

		// read test script data from excel file

		String orgname = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();


		// step4:enter all the details and create new Organization
		UtilityClassObject.getTest().log(Status.INFO, "create a new org");

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
	}

	@Test
	public void CreateOrganizationWithIndustriesTest() throws Exception {

		// read test script data from excel file

		String orgname = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();
		String industry = elib.getDataFromExcel("org", 4, 3);
		String type = elib.getDataFromExcel("org", 4, 4);

		// step2:navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();

		// step3:click on create organization button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNeworgBtn().click();

		// step4:enter the details& create organization

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createorg(orgname, industry, type);

//				driver.findElement(By.name("accountname")).sendKeys(orgname);
//				//FOR INDUSTRY dropdown
//				WebElement INDUSTRYDD = driver.findElement(By.name("industry"));
//				Select industrydropdown=new Select(INDUSTRYDD);
//				industrydropdown.selectByVisibleText(industry);
//				
//				//For Type dropdown
//				WebElement TYPEDD = driver.findElement(By.name("accounttype"));
//				Select typedropdown=new Select(TYPEDD);
//				typedropdown.selectByVisibleText(type
//				
//				//save
//				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// step5:verify organization name in the header of the msg
		
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String header = oip.getHeaderMsg().getText();
		//String header = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		System.out.println(header);

		if (header.contains(orgname)) {
			System.out.println("organization created successfully");
		} else {
			System.out.println("Failed to create organization");
		}

		// verify the industries and type info
		
		String actIndustries = oip.getIndustrydata().getText();
		//String actIndustries = driver.findElement(By.id("dtlview_Industry")).getText();
		if (actIndustries.equals(industry)) {
			System.out.println(industry + "  information is verified==PASS");
		} else {
			System.out.println(industry + " information is not verified ==FAIL");
		}
		
		String actType = oip.getTypedata().getText();

		//String actType = driver.findElement(By.id("dtlview_Type")).getText();
		if (actType.equals(type)) {
			System.out.println(type + " information is verified==PASS");
		} else {
			System.out.println(type + " information is not verified ==FAIL");
		}

	}

	@Test
	public void CreateOrganizationWithPhoneNumberTest() throws Exception {

		// read test script data from excel file

		String orgname = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();

		String phoneno = elib.getDataFromExcel("org", 7, 3);

		// step2:navigate to organization module

		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();

		// step3:click on create organization button

		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNeworgBtn().click();

		// step4:enter the details& create organization

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createorgwithphone(orgname, phoneno);

		// step5:verify organization name in the header of the msg
		
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String header = oip.getHeaderMsg().getText();
		//String header = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		System.out.println(header);

		if (header.contains(orgname)) {
			System.out.println("organization created successfully");
		} else {
			System.out.println("Failed to create organization");
		}

		// verify Header orgname info expected result
		
		String actOrgName = oip.getOrgnamedata().getText();
		//String actOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
		if (actOrgName.equals(orgname)) {
			System.out.println(orgname + " is created==PASS");
		} else {
			System.out.println(orgname + " is not created ==FAIL");
		}

		// verify Header phone number info expected result
		
		 String actphoneno = oip.getPhonedata().getText();
		//String actphoneno = driver.findElement(By.id("dtlview_Phone")).getText();
		if (actphoneno.equals(phoneno)) {
			System.out.println(phoneno + "  information is created==PASS");
		} else {
			System.out.println(phoneno + "  information is not created ==FAIL");
		}

	}

}
