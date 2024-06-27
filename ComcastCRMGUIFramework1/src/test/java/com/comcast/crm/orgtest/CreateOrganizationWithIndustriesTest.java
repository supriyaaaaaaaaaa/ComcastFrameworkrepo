package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateOrganizationWithIndustriesTest {

	public static void main(String[] args) throws Exception {
		// create object properties file class
		FileUtility flib = new FileUtility();

		// create object excel file class
		ExcelUtility elib = new ExcelUtility();
		
		//create object for javautility class
		JavaUtility jlib= new JavaUtility();
		
		// read common data from properties file
		String URL = flib.getDataFromPropertiesFile("url");
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");

		
		// read test script data from excel file
		
		String orgname =   elib.getDataFromExcel("org", 1, 2)  + jlib.getRandomNumber();
		String industry =   elib.getDataFromExcel("org", 4, 3) ;
		String type =   elib.getDataFromExcel("org", 4, 4) ;
		

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
		//FOR INDUSTRY dropdown
		WebElement INDUSTRYDD = driver.findElement(By.name("industry"));
		Select industrydropdown=new Select(INDUSTRYDD);
		industrydropdown.selectByVisibleText(industry);
		
		//For Type dropdown
		WebElement TYPEDD = driver.findElement(By.name("accounttype"));
		Select typedropdown=new Select(TYPEDD);
		typedropdown.selectByVisibleText(type);
		
		//save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// step5:verify organization name in the header of the msg
		String header = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		System.out.println(header);

		if (header.contains(orgname)) {
			System.out.println("organization created successfully");
		} else {
			System.out.println("Failed to create organization");
		}

		// verify the industries and type info 
		String actIndustries = driver.findElement(By.id("dtlview_Industry")).getText();
		if (actIndustries.equals(industry)) {
			System.out.println(industry + "  information is verified==PASS");
		} else {
			System.out.println(industry + " information is not verified ==FAIL");
		}
		
		String actType = driver.findElement(By.id("dtlview_Type")).getText();
		if (actType.equals(type)) {
			System.out.println(type + " information is verified==PASS");
		} else {
			System.out.println(type + " information is not verified ==FAIL");
		}
		
		
		// step6:logout
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();

	}

}
