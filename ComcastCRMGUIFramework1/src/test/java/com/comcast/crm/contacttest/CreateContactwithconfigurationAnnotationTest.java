package com.comcast.crm.contacttest;

import java.sql.Driver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationNewWindowPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;


/**
 * 
 * this class is related to contact  module
 * @author HP
 *
 */
public class CreateContactwithconfigurationAnnotationTest extends BaseClass {
	@Test
	public void createContactTest() throws Exception {

		/* read test script data from excel file*/ 

		String LASTNAME = elib.getDataFromExcel("contact", 1, 2);

		// step2:navigate to contact module
		HomePage hp = new HomePage(driver);
		hp.getContactlink().click();

		// step3:click on "create contact" Button

		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();

		// step4:enter all the details and create new Organization
		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
		ccp.createcontact(LASTNAME);

		// verify Header msg Expected Result

		ContactInfoPage cip = new ContactInfoPage(driver);
		String actlastname = cip.getContactheadermsg().getText();
//		if (actlastname.contains(LASTNAME)) {
//			System.out.println(LASTNAME + "  header is verified==PASS");
//		}
//
//		else {
//			System.out.println(LASTNAME + "  header is verified==FAIL");
//		}

		// Assertion//hard assert
		boolean satus = actlastname.contains(LASTNAME);

		Assert.assertEquals(satus, true);

		// verify lastname edit

		String actlastnameedit = cip.getLastnameeditdata().getText();

		// String actlastnameedit = driver.findElement(By.id("dtlview_Last
		// Name")).getText();

//		if (actlastname.equals(LASTNAME)) {
//			System.out.println(LASTNAME + "  information is verified==PASS");
//		} else {
//			System.out.println(LASTNAME + " is not verified==FAIL");
//		}

		// soft assert
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actlastnameedit, LASTNAME);

	}

	@Test
	public void CreateContactWithOrgTest() throws Exception {

		// to see the log into extent report
		UtilityClassObject.getTest().log(Status.INFO, "read data from excel");

		// read test script data from excel file

		String ContactLastName = elib.getDataFromExcel("contact", 7, 3);
		String orgname = elib.getDataFromExcel("contact", 7, 2) + jlib.getRandomNumber();

	 	// step2:navigate to organization module

		// to see the log into extent report
		UtilityClassObject.getTest().log(Status.INFO, "navigate to org page");

		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();

		// step3:click on create organization button
		// to see the log into extent report
		UtilityClassObject.getTest().log(Status.INFO, "navigate to  create org page");

		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNeworgBtn().click();

		// step4:enter the details& create organization
		UtilityClassObject.getTest().log(Status.INFO, " create a new  org");

		
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createorg(orgname);

		// step5:verify organization name in the header of the msg

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String header = oip.getHeaderMsg().getText();
		System.out.println(header);

//		String header = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
//		System.out.println(header);

		if (header.contains(orgname)) {
			System.out.println("organization created successfully");
		} else {
			System.out.println("Failed to create organization");
		}
		// Navigate to contact module

		// step2:navigate to Contacts module
		hp.getContactlink().click();

		// step3:click on create Contacts button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();

		// step4:enter the details& create Contacts
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.createContactWithOrg(ContactLastName, orgname);

		// switch to child window

		wlib.switchToTabOnURL(driver, "module=Accounts");
		OrganizationNewWindowPage onp = new OrganizationNewWindowPage(driver);
		// onp.getSearchedit().sendKeys(orgname);
		onp.searchtxtfield(orgname);

		driver.findElement(By.xpath("//a[text()='" + orgname + "']")).click();

		// switch to parent window

		wlib.switchToTabOnURL(driver, "module=Contacts");

		// save
		cncp.getSavebtn().click();

		// step5:verify Contacts name in the header of the msg

		ContactInfoPage cip = new ContactInfoPage(driver);
		String actlastname = cip.getContactheadermsg().getText();

//		String actlastname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		System.out.println(actlastname);

		if (actlastname.contains(ContactLastName)) {
			System.out.println(ContactLastName + " header contacts created successfully");
		} else {
			System.out.println(ContactLastName + "  header Failed to create contacts");
		}

		// verify orgname info

		String actOrgName = cip.getOrgedit().getText();
		// String actOrgName = driver.findElement(By.id("mouseArea_Organization
		// Name")).getText();
		if (actOrgName.contains(orgname)) {
			System.out.println(orgname + " information is created==PASS");
		} else {
			System.out.println(orgname + "information is not created==FAIL");
		}

	}

	@Test
	public void CreateContactWithSupportDateTest() throws Exception {

		// read test script data from excel file

		String LASTNAME = elib.getDataFromExcel("contact", 4, 2);

		// step2:navigate to contacts module
		HomePage hp = new HomePage(driver);
		hp.getContactlink().click();

		// step3:click on create contact button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();

		// step4:enter the details& create contact

		String StartDate = jlib.getSystemDateYYYYDDMM();
		String endDate = jlib.getRequiredDateYYDDMM(30);
		CreatingNewContactPage cnp = new CreatingNewContactPage(driver);

		cnp.date(LASTNAME, StartDate, endDate);

		// step5:verify contact name in the header of the msg

		ContactInfoPage cip = new ContactInfoPage(driver);
		String header = cip.getContactheadermsg().getText();
		// String header =
		// driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		System.out.println(header);

//			if (header.contains(LASTNAME)) {
//				System.out.println(header + "  header contacts created successfully");
//			} else {
//				System.out.println(header +"  header Failed to create contacts");
//			}

		// Assertion

		// verify LASTNAME edit info expected result

		String actlastname = cip.getLastnameeditdata().getText();
		// String actlastname = driver.findElement(By.id("dtlview_Last
		// Name")).getText();
		if (actlastname.equals(LASTNAME)) {
			System.out.println(LASTNAME + "  information is verified==PASS");
		} else {
			System.out.println(LASTNAME + " is not verified==FAIL");
		}

		// verify start date and end date info

		String actSSD = cip.getSsddata().getText();
		// String actSSD = driver.findElement(By.id("dtlview_Support Start
		// Date")).getText();
		System.out.println(actSSD);

		if (actSSD.equals(StartDate)) {
			System.out.println(StartDate + " information is verified==pass");
		} else {
			System.out.println(StartDate + " is not verified==FAIL");
		}

		String actSeD = cip.getSeddata().getText();

		// String actSeD = driver.findElement(By.id("dtlview_Support End
		// Date")).getText();
		System.out.println(actSeD);

		if (actSeD.equals(endDate)) {
			System.out.println(endDate + " information is verified==pass");
		} else {
			System.out.println(endDate + " is not verified==FAIL");
		}

	}

}
