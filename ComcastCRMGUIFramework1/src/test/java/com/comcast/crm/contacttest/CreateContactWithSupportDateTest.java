package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateContactWithSupportDateTest {

	public static void main(String[] args) throws Exception {
		
		// create object properties file class
		FileUtility flib = new FileUtility();
		
		// create object excel file class
		ExcelUtility elib = new ExcelUtility();
		 
		//create object for javautility class
		JavaUtility jlib= new JavaUtility();

		
	
		String URL = flib.getDataFromPropertiesFile("url");
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");

		
		// read test script data from excel file
	
		String LASTNAME = elib.getDataFromExcel("contact", 4, 2); 

		

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
		// step2:navigate to contacts module

		driver.findElement(By.linkText("Contacts")).click();
		// step3:click on create contact button

		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		// step4:enter the details& create contact

		String StartDate = jlib.getSystemDateYYYYDDMM();
		String endDate = jlib.getRequiredDateYYDDMM(30);

		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		// support_start_date
		WebElement ssd = driver.findElement(By.name("support_start_date"));
		ssd.clear();
		ssd.sendKeys(StartDate);
		// support_end_date
		WebElement sed = driver.findElement(By.name("support_end_date"));
		sed.clear();
		sed.sendKeys(endDate);
		
		//save

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// step5:verify contact name in the header of the msg
		String header = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		System.out.println(header);

		if (header.contains(LASTNAME)) {
			System.out.println("contacts created successfully");
		} else {
			System.out.println("Failed to create contacts");
		}

		// verify Header LASTNAME info expected result
		String actlastname = driver.findElement(By.id("dtlview_Last Name")).getText();
		if (actlastname.equals(LASTNAME)) {
			System.out.println(LASTNAME + "  information is verified==PASS");
		} else {
			System.out.println(LASTNAME + " is not verified==FAIL");
		}
		
		//verify start date and end date info 
		String actSSD = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		System.out.println(actSSD);
		
		if(actSSD.equals(StartDate)) {
			System.out.println(StartDate+" information is verified==pass");
		}
		else {
			System.out.println(StartDate + " is not verified==FAIL");
		}
		
		String actSeD = driver.findElement(By.id("dtlview_Support End Date")).getText();
		System.out.println(actSeD);
		
		if(actSeD.equals(endDate)) {
			System.out.println(endDate+" information is verified==pass");
		}
		else {
			System.out.println(endDate + " is not verified==FAIL");
		}
		
		
		// step6:logout
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();

	}
}
