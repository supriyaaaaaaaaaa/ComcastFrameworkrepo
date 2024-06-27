package com.comcast.crm.contacttest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContactWithOrgTestngTest {
	
	@Test
	public void CreateContactWithOrgTest() throws Exception {
		
	// create object properties file class
	FileUtility flib = new FileUtility();

	// create object excel file class
	ExcelUtility elib = new ExcelUtility();

	// create object for javautility class
	JavaUtility jlib = new JavaUtility();

	// create object for webdriverutility class
	WebDriverUtility wlib = new WebDriverUtility();

	String URL = flib.getDataFromPropertiesFile("url");
	String BROWSER = flib.getDataFromPropertiesFile("browser");
	String USERNAME = flib.getDataFromPropertiesFile("username");
	String PASSWORD = flib.getDataFromPropertiesFile("password");

	// read test script data from excel file

	String ContactLastName = elib.getDataFromExcel("contact", 7, 3);
	String orgname = elib.getDataFromExcel("contact", 7, 2) + jlib.getRandomNumber();
	

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
	wlib.waitForPageToLoad(driver); 
	// step1:login to app
	driver.get(URL);

	driver.findElement(By.name("user_name")).sendKeys(USERNAME);
	driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
	driver.findElement(By.id("submitButton")).click();
	// step2:navigate to organization module

	driver.findElement(By.linkText("Organizations")).click();
	// step3:click on create organization button

	driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
	// step4:enter the details& create organization

	driver.findElement(By.name("accountname")).sendKeys(orgname);

	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

	// step5:verify organization name in the header of the msg
	String header = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	System.out.println(header);

	if (header.contains(orgname)) {
		System.out.println("organization created successfully");
	} else {
		System.out.println("Failed to create organization");
	}
	// Navigate to contact module

	// step2:navigate to Contacts module

	driver.findElement(By.linkText("Contacts")).click();

	// step3:click on create Contacts button

	driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
	// step4:enter the details& create Contacts

	driver.findElement(By.name("lastname")).sendKeys(ContactLastName);
	driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
	// switch to child window

	wlib.switchToTabOnURL(driver, "module=Accounts");

	driver.findElement(By.id("search_txt")).sendKeys(orgname);
	driver.findElement(By.name("search")).click();
	driver.findElement(By.xpath("//a[text()='" + orgname + "']")).click();

	// switch to parent window

	wlib.switchToTabOnURL(driver, "module=Contacts");

	// save
	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

	// step5:verify Contacts name in the header of the msg

	String actlastname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	System.out.println(actlastname);

	if (actlastname.contains(ContactLastName)) {
		System.out.println(ContactLastName + "  contacts created successfully");
	} else {
		System.out.println(ContactLastName + "  Failed to create contacts");
	}

	// verify orgname info
	String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
	if (actOrgName.contains(orgname)) {
		System.out.println(orgname + " information is created==PASS");
	} else {
		System.out.println(orgname + "information is not created==FAIL");
	}

	// logout
	Actions act = new Actions(driver);
	act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
	driver.findElement(By.linkText("Sign Out")).click();
	driver.quit();

}

}



